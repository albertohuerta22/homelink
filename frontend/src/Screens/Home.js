import React, { useState, useEffect } from 'react';
import { getShelters } from '../Services/shelterApi/shelterApi';

const Home = () => {
  const [shelters, setShelters] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getShelters();
        console.log(response);
        setShelters(response);
      } catch (error) {
        console.log('Error fetching data', error);
      }
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
            <li>{shelter.name}</li>
            <li>
              There are currently: {shelter.capacity} open slots at{' '}
              {shelter.location}
            </li>
            <br />
          </div>
        ))}
      </ul>
    </div>
  );
};

export default Home;
