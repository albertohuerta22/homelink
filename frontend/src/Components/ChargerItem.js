import React from 'react';
import ListGroup from 'react-bootstrap/ListGroup';

const ChargerItem = ({ charger }) => (
  <ListGroup.Item className="mb-1 list-item-hover">
    <strong>Charger ID: {charger.id}</strong>
    <br />
    {charger.streetAddress}
    <br />
    Location: {charger.location}
  </ListGroup.Item>
);

export default ChargerItem;
