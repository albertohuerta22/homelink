import React, { useState, useEffect } from 'react';
import { getShelters } from '../Services/shelterApi/shelterApi';

const Home = () => {
  const [shelters, setShelters] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      // Define a unique key for the shelters data cache
      const cacheKey = 'sheltersData';
      // Try to get cached shelters data
      const cachedData = localStorage.getItem(cacheKey);
      let sheltersData;

      if (cachedData) {
        // Parse the cached JSON data
        sheltersData = JSON.parse(cachedData);
        // Optionally, you could implement a cache validation check here as well
      }

      if (!sheltersData) {
        try {
          const response = await getShelters();
          sheltersData = response;
          // Store the new data in local storage
          localStorage.setItem(cacheKey, JSON.stringify(sheltersData));
        } catch (error) {
          console.log('Error fetching data', error);
          return;
        }
      }

      // Set state with either cached or fetched data
      setShelters(sheltersData);
    };

    fetchData();
  }, []);

  return (
    <div>
      <h1>Welcome to Home Link! Your resource to finding shelter!</h1>
      <h2>List of Shelters</h2>
      <ul>
        {shelters.map((shelter) => (
          <div key={shelter.id}>
            <li>{shelter.centerName}</li>
            <li>{shelter.address}</li>
            <li>{shelter.borough}</li>
            <br />
          </div>
        ))}
      </ul>
    </div>
  );
};

export default Home;
