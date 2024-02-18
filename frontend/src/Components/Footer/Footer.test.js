import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom';
import Footer from './Footer'; // Adjust the import path as necessary

describe('Footer Component', () => {
  test('renders the footer with all sections', () => {
    render(<Footer />);

    // Check for section headings
    expect(screen.getByText('NYC Contacts')).toBeInTheDocument();
    expect(screen.getByText('Emergency Contacts')).toBeInTheDocument();
    expect(screen.getByText('About')).toBeInTheDocument();
    expect(screen.getByText('Jobs')).toBeInTheDocument();
    expect(screen.getByText('Contact Us')).toBeInTheDocument();
    expect(screen.getByText('Contribute')).toBeInTheDocument();

    // Check for specific links
    expect(screen.getByText('Office Locations')).toHaveAttribute('href', '#!');
    expect(screen.getByText('Police Department')).toHaveAttribute('href', '#!');
    expect(screen.getByText('Our Mission')).toHaveAttribute('href', '#!');
    expect(screen.getByText('Career Opportunities')).toHaveAttribute(
      'href',
      '#!'
    );
    expect(screen.getByText('Customer Service')).toHaveAttribute('href', '#!');
    expect(screen.getByText('Donate')).toHaveAttribute('href', '#!');

    // Check for contact field
    expect(screen.getByPlaceholderText('Enter email')).toBeInTheDocument();
    expect(screen.getByRole('button', { name: 'Submit' })).toBeInTheDocument();
  });
});
