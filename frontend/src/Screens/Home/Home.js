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
  const chargersPerPage = 20;

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
          setTotalPages(
            Math.ceil(JSON.parse(cachedChargers).length / chargersPerPage)
          );
        } else {
          try {
            const response = await getChargers();

            if (Array.isArray(response)) {
              localStorage.setItem(chargersCacheKey, JSON.stringify(response));
              setChargers(response);
              setTotalPages(Math.ceil(response.length / chargersPerPage));
            } else {
              console.error(
                'Expected an array from the API, received:',
                response
              );
            }
          } catch (error) {
            console.error('Error fetching chargers:', error);
          }
        }
      }
    };

    fetchChargers();
  }, [currentView, chargersPerPage]);

  const handleViewToggle = () => {
    setCurrentView(currentView === 'shelters' ? 'chargers' : 'shelters');
  };

  const maxButtonsToShow = 10; // Adjust as needed
  let startPage = Math.max(currentPage - Math.floor(maxButtonsToShow / 2), 1);
  let endPage = Math.min(startPage + maxButtonsToShow - 1, totalPages);

  if (endPage === totalPages) {
    startPage = Math.max(endPage - maxButtonsToShow + 1, 1);
  }

  let paginationItems = [];

  // Ellipsis for skipping to previous range if not at the start
  if (startPage > 1) {
    paginationItems.push(
      <Pagination.Ellipsis
        key="prev-ellipsis"
        onClick={() => setCurrentPage(startPage - 1)}
      />
    );
  }

  for (let number = startPage; number <= endPage; number++) {
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

  // Ellipsis for skipping to next range if not at the end
  if (endPage < totalPages) {
    paginationItems.push(
      <Pagination.Ellipsis
        key="next-ellipsis"
        onClick={() => setCurrentPage(endPage + 1)}
      />
    );
  }

  const getPageChargers = () => {
    const startIndex = (currentPage - 1) * chargersPerPage; // Calculate the starting index for the current page
    const endIndex = startIndex + chargersPerPage; // Calculate the ending index for the current page

    // Directly slice the relevant chargers from the flat array for the current page
    const pageChargers = chargers.slice(startIndex, endIndex);

    return pageChargers;
  };

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
          : getPageChargers().map((charger) => (
              <ChargerItem key={charger.id} charger={charger} />
            ))}
      </ListGroup>
      {currentView === 'chargers' && totalPages > 1 && (
        <Pagination className="justify-content-center mt-3">
          {paginationItems}
        </Pagination>
      )}
    </div>
  );
};

export default Home;
