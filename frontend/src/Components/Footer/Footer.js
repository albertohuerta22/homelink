import React from 'react';
import { Container, Row, Col, Form, Button } from 'react-bootstrap';

import './Footer.css';

const Footer = () => {
  return (
    <footer className="footer-section">
      <Container>
        <Row>
          <Col xs={12} md={2}>
            <h5>NYC Contacts</h5>
            <ul>
              <li>
                <a href="#!">Office Locations</a>
              </li>
              <li>
                <a href="#!">Subway Info</a>
              </li>
              <li>
                <a href="#!">Parking Services</a>
              </li>
              <li>
                <a href="#!">City Map</a>
              </li>
            </ul>
          </Col>
          <Col xs={12} md={2}>
            <h5>Emergency Contacts</h5>
            <ul>
              <li>
                <a href="#!">Police Department</a>
              </li>
              <li>
                <a href="#!">Fire Department</a>
              </li>
              <li>
                <a href="#!">Medical Services</a>
              </li>
              <li>
                <a href="#!">Disaster Response</a>
              </li>
            </ul>
          </Col>
          <Col xs={12} md={2}>
            <h5>About</h5>
            <ul>
              <li>
                <a href="#!">Our Mission</a>
              </li>
              <li>
                <a href="#!">Leadership</a>
              </li>
              <li>
                <a href="#!">History</a>
              </li>
              <li>
                <a href="#!">Achievements</a>
              </li>
            </ul>
          </Col>
          <Col xs={12} md={2}>
            <h5>Jobs</h5>
            <ul>
              <li>
                <a href="#!">Career Opportunities</a>
              </li>
              <li>
                <a href="#!">Internships</a>
              </li>
              <li>
                <a href="#!">Volunteering</a>
              </li>
              <li>
                <a href="#!">HR Contact</a>
              </li>
            </ul>
          </Col>
          <Col xs={12} md={2}>
            <h5>Contact Us</h5>
            <ul>
              <li>
                <a href="#!">Customer Service</a>
              </li>
              <li>
                <a href="#!">Support Center</a>
              </li>
              <li>
                <a href="#!">Feedback</a>
              </li>
              <li>
                <a href="#!">FAQs</a>
              </li>
            </ul>
          </Col>
          <Col xs={12} md={2}>
            <h5>Contribute</h5>
            <ul>
              <li>
                <a href="#!">Donate</a>
              </li>
              <li>
                <a href="#!">Sponsorship</a>
              </li>
              <li>
                <a href="#!">Partnerships</a>
              </li>
              <li>
                <a href="#!">Community Programs</a>
              </li>
            </ul>
          </Col>
        </Row>
        <Row className="mt-4">
          <Col xs={12} md={6}>
            <h5>Contact Field</h5>
            <Form>
              <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Control type="email" placeholder="Enter email" />
              </Form.Group>
              <Button variant="primary" type="submit">
                Submit
              </Button>
            </Form>
          </Col>
        </Row>
      </Container>
    </footer>
  );
};

export default Footer;
