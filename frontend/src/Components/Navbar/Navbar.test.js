import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import CustomNavbar from './Navbar'; // Adjust the import path as necessary

// Mock the Weather component to avoid testing its internal behavior here
jest.mock('../Weather/Weather', () => () => <div>WeatherComponentMock</div>);

describe('CustomNavbar Component', () => {
  test('renders the navbar with brand and links', () => {
    render(
      <Router>
        <CustomNavbar />
      </Router>
    );

    // Check for the Navbar brand
    expect(screen.getByText('HomeLink')).toBeInTheDocument();

    // Check for navigation links
    const homeLink = screen.getByRole('link', { name: 'Home' });
    const mapViewLink = screen.getByRole('link', { name: 'Map View' });
    const aboutLink = screen.getByRole('link', { name: 'About' });
    const contactLink = screen.getByRole('link', { name: 'Contact' });

    expect(homeLink).toBeInTheDocument();
    expect(mapViewLink).toBeInTheDocument();
    expect(aboutLink).toBeInTheDocument();
    expect(contactLink).toBeInTheDocument();

    // Check for the Weather component
    expect(screen.getByText('WeatherComponentMock')).toBeInTheDocument();
  });

  // Add more tests as needed, for example, to simulate user interactions or test responsive behavior
});
