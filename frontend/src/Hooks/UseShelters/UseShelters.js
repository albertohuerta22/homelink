import { useState, useEffect } from 'react';
import { getShelters } from '../../Services/shelterApi/shelterApi';

const UseShelters = () => {
  const [shelters, setShelters] = useState([]);

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

  return shelters;
};

export default UseShelters;
