import React from 'react';
import ListGroup from 'react-bootstrap/ListGroup';

const ShelterItem = ({ shelter }) => (
  <ListGroup.Item className="mb-1 list-item-hover">
    <strong>{shelter.centerName}</strong>
    <br />
    {shelter.address}
    <br />
    {shelter.borough}
  </ListGroup.Item>
);

export default ShelterItem;
