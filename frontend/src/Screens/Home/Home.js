import React, { useState, useEffect } from 'react';
import { getShelters } from '../../Services/shelterApi/shelterApi';
import ListGroup from 'react-bootstrap/ListGroup';

//styling
import './Home.css';

const Home = () => {
  const [shelters, setShelters] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const cacheKey = 'sheltersData';
      const cachedData = localStorage.getItem(cacheKey);
      let sheltersData;

      if (cachedData) {
        sheltersData = JSON.parse(cachedData);
      }

      if (!sheltersData) {
        try {
          const response = await getShelters();
          sheltersData = response;
          localStorage.setItem(cacheKey, JSON.stringify(sheltersData));
        } catch (error) {
          console.log('Error fetching data', error);
          return;
        }
      }

      setShelters(sheltersData);
    };

    fetchData();
  }, []);

  //styles
  const listItemStyle = {
    cursor: 'pointer',
  };

  return (
    <div>
      <h1>Welcome to Home Link! Your resource to finding shelter!</h1>
      <h2 className="text-center">List of Shelters</h2>
      <ListGroup>
        {shelters.map((shelter) => (
          <ListGroup.Item
            key={shelter.id}
            className="mb-1 list-group-item-action list-item-hover"
            style={listItemStyle}
          >
            <strong>{shelter.centerName}</strong>
            <br />
            {shelter.address}
            <br />
            {shelter.borough}
          </ListGroup.Item>
        ))}
      </ListGroup>
    </div>
  );
};

export default Home;
