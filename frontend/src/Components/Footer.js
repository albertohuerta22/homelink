import React from 'react';
import { Link } from 'react-router-dom';

const Footer = () => {
  return (
    <footer style={footerStyle}>
      {/* NYC Contacts Section */}
      <div style={sectionStyle}>
        <h3>NYC Contacts</h3>
        <ul style={listStyle}>
          <li>
            <Link to="/nyc/contact1">Contact 1</Link>
          </li>
          <li>
            <Link to="/nyc/contact2">Contact 2</Link>
          </li>
          {/* Add more links as needed */}
        </ul>
      </div>

      {/* Emergency Contacts Section */}
      <div style={sectionStyle}>
        <h3>Emergency Contacts</h3>
        <ul style={listStyle}>
          <li>
            <Link to="/emergency/contact1">Emergency Contact 1</Link>
          </li>
          <li>
            <Link to="/emergency/contact2">Emergency Contact 2</Link>
          </li>
          {/* Add more links as needed */}
        </ul>
      </div>
    </footer>
  );
};

// Styles
const footerStyle = {
  display: 'flex',
  justifyContent: 'space-between',
  borderTop: 'solid',
  padding: '10px',
};

const sectionStyle = {
  flex: 1,
};

const listStyle = {
  listStyleType: 'none',
  padding: 0,
};

export default Footer;
