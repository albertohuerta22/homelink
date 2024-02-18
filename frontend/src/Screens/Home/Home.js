import React, { useState } from 'react';
import { Container, Row, Col, Card, Button } from 'react-bootstrap';
import UseShelters from '../../Hooks/UseShelters/UseShelters';
import UseChargers from '../../Hooks/UseChargers';
import CustomPagination from '../../Hooks/CustomPagination/CustomPagination';
import ChargerItem from '../../Components/ChargerItem';
import shelterImages from '../../Util/shelterImages';

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
    <Container>
      <Row className="mb-4 welcome-section">
        <Col>
          <h1 className="text-center header-title">
            Your resource for finding shelters and chargers!
          </h1>
        </Col>
      </Row>
      <Row>
        <Col className="text-start mb-3">
          <Button onClick={handleViewToggle}>
            {currentView === 'shelters' ? 'Show Chargers' : 'Show Shelters'}
          </Button>
        </Col>
      </Row>
      <Row xs={1} md={2} lg={3} className="g-4 cards-container">
        {currentView === 'shelters'
          ? shelters.map((shelter) => (
              <Col key={shelter.id}>
                <Card>
                  <Card.Img
                    variant="top"
                    src={
                      shelterImages[shelter.centerName] ||
                      'https://plus.unsplash.com/premium_photo-1683141149687-7c8de76522fe?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'
                    }
                  />
                  <Card.Body>
                    <Card.Title>{shelter.centerName}</Card.Title>
                    <Card.Text>{shelter.address}</Card.Text>
                    {/* Render shelter.comments here */}
                    {shelter.comments && (
                      <Card.Text className="shelter-comments">
                        {shelter.comments}
                      </Card.Text>
                    )}
                  </Card.Body>
                </Card>
              </Col>
            ))
          : getPageChargers().map((charger) => (
              <Col key={charger.id}>
                <ChargerItem key={charger.id} charger={charger} />
              </Col>
            ))}
      </Row>
      {currentView === 'chargers' && totalPages > 1 && (
        <CustomPagination
          currentPage={currentPage}
          totalPages={totalPages}
          onPageChange={setCurrentPage}
        />
      )}
    </Container>
  );
};

export default Home;
