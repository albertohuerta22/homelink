// Navbar.js
import React from 'react';
import { Link } from 'react-router-dom'; // Import Link for navigation

import Weather from './Weather';

function Navbar() {
  return (
    <nav>
      <ul>
        <li>
          <Link to="/">Home</Link>
        </li>
        <li>
          <Link to="/map">Map View</Link>
        </li>
        <li>
          <Link to="/about">About</Link>
        </li>
        <li>
          <Link to="/contact">Contact</Link>
        </li>
      </ul>
      <Weather />
    </nav>
  );
}

export default Navbar;
