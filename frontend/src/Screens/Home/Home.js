import React, { useState } from 'react';
import ListGroup from 'react-bootstrap/ListGroup';
import Button from 'react-bootstrap/Button';
import UseShelters from '../../Hooks/UseShelters';
import UseChargers from '../../Hooks/UseChargers';
import CustomPagination from '../../Hooks/CustomPagination';
import ChargerItem from '../../Components/ChargerItem';
import ShelterItem from '../../Components/ShelterItem';

import './Home.css';

const Home = () => {
  const [currentPage, setCurrentPage] = useState(1);
  const [currentView, setCurrentView] = useState('shelters');
  const shelters = UseShelters();
  const { totalPages, getPageChargers } = UseChargers(currentView, currentPage);

  const handleViewToggle = () => {
    setCurrentView(currentView === 'shelters' ? 'chargers' : 'shelters');
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
        <CustomPagination
          currentPage={currentPage}
          totalPages={totalPages}
          onPageChange={setCurrentPage}
        />
      )}
    </div>
  );
};

export default Home;
