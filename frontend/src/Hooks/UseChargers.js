import { useState, useEffect } from 'react';
import { getChargers } from '../Services/chargerApi/chargerApi';

const UseChargers = (currentView, currentPage) => {
  const [chargers, setChargers] = useState([]);
  const [totalPages, setTotalPages] = useState(0);
  const chargersPerPage = 10;

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

  const getPageChargers = () => {
    const startIndex = (currentPage - 1) * chargersPerPage; // Calculate the starting index for the current page
    const endIndex = startIndex + chargersPerPage; // Calculate the ending index for the current page

    // Directly slice the relevant chargers from the flat array for the current page
    const pageChargers = chargers.slice(startIndex, endIndex);

    return pageChargers;
  };

  return { chargers, totalPages, setTotalPages, getPageChargers };
};

export default UseChargers;
