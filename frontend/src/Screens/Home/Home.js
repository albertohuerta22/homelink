import React, { useState, useEffect } from 'react';
import ListGroup from 'react-bootstrap/ListGroup';
import Button from 'react-bootstrap/Button';
import Pagination from 'react-bootstrap/Pagination';
import { getShelters } from '../../Services/shelterApi/shelterApi';
import { getChargers } from '../../Services/chargerApi/chargerApi';

import './Home.css';

const ShelterItem = ({ shelter }) => (
  <ListGroup.Item className="mb-1 list-item-hover">
    <strong>{shelter.centerName}</strong>
    <br />
    {shelter.address}
    <br />
    {shelter.borough}
  </ListGroup.Item>
);

const ChargerItem = ({ charger }) => (
  <ListGroup.Item className="mb-1 list-item-hover">
    <strong>Charger ID: {charger.id}</strong>
    <br />
    {charger.streetAddress}
    <br />
    Location: {charger.location}
  </ListGroup.Item>
);

const Home = () => {
  const [currentView, setCurrentView] = useState('shelters');
  const [shelters, setShelters] = useState([]);
  const [chargers, setChargers] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const chargersPerPage = 10;

  useEffect(() => {
    const sheltersCacheKey = 'sheltersData';
    const fetchShelters = async () => {
      const cachedShelters = localStorage.getItem(sheltersCacheKey);
      if (cachedShelters) {
        setShelters(JSON.parse(cachedShelters));
      } else {
        const response = await getShelters();
        localStorage.setItem(sheltersCacheKey, JSON.stringify(response));
        setShelters(response);
      }
    };
    fetchShelters();
  }, []);

  useEffect(() => {
    const chargersCacheKey = 'chargersData';
    const fetchChargers = async () => {
      if (currentView === 'chargers') {
        const cachedChargers = localStorage.getItem(chargersCacheKey);
        if (cachedChargers) {
          setChargers(JSON.parse(cachedChargers));
        } else {
          try {
            const response = await getChargers(); // Fetch all chargers
            localStorage.setItem(chargersCacheKey, JSON.stringify(response));
            setChargers(response);
            setTotalPages(Math.ceil(response.length / chargersPerPage)); // Calculate total pages based on the response
          } catch (error) {
            console.error('Error fetching chargers:', error);
          }
        }
      }
    };

    fetchChargers();
  }, [currentView]);

  const handleViewToggle = () => {
    setCurrentView(currentView === 'shelters' ? 'chargers' : 'shelters');
  };

  let paginationItems = [];
  for (let number = 1; number <= totalPages + 1; number++) {
    paginationItems.push(
      <Pagination.Item
        key={number}
        active={number === currentPage}
        onClick={() => setCurrentPage(number)}
      >
        {number}
      </Pagination.Item>
    );
  }

  return (
    <div>
      <h1 className="text-center">
        Welcome to Home Link! Your resource for finding shelters and chargers!
      </h1>
      <Button onClick={handleViewToggle} className="mb-3">
        {currentView === 'shelters' ? 'Show Chargers' : 'Show Shelters'}
      </Button>
      <ListGroup>
        {currentView === 'shelters'
          ? shelters.map((shelter) => (
              <ShelterItem key={shelter.id} shelter={shelter} />
            ))
          : chargers
              .slice(
                (currentPage - 1) * chargersPerPage,
                currentPage * chargersPerPage
              )
              .map((charger) => (
                <ChargerItem key={charger.id} charger={charger} />
              ))}
      </ListGroup>
      {currentView === 'chargers' && (
        <Pagination className="justify-content-center mt-3">
          {paginationItems}
        </Pagination>
      )}
    </div>
  );
};

export default Home;
