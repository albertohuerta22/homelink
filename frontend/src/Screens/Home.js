import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Home = () => {
  const [shelters, setShelters] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:3001/api/Shelter');

        setShelters(response.data);
      } catch (error) {
        console.log('Error fetching data', error);
      }
    };
    fetchData();
  }, []);

  return (
    <>
      <h1>Welcome to Home Link! Your resource to finding shelter!</h1>
      <div>
        <h2>List of Shelters</h2>
        <ul>
          {shelters.map((shelter) => (
            <>
              <li key={shelter.id}>{shelter.name}</li>
              <li>
                There are currently: {shelter.capacity} open slots at{' '}
                {shelter.location}
              </li>
              <br />
            </>
          ))}
        </ul>
      </div>
    </>
  );
};

export default Home;
