import React from 'react';
import { Link } from 'react-router-dom'; // Import Link for navigation
import './NotFound.css';

const NotFound = () => {
  return (
    <div className="not-found-container">
      <h1 className="not-found-title">404</h1>
      <p className="not-found-text">
        Oops! The page you're looking for isn't here.
      </p>
      <Link to="/" className="not-found-home-link">
        Take me home
      </Link>
    </div>
  );
};

export default NotFound;
