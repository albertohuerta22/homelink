import React from 'react';
import './About.css';
import supportImage from '../../Assets/images/supportImage.png';

const About = () => {
  return (
    <div className="about-container">
      <div className="image-header">
        <img src={supportImage} alt="Support for the Unhoused Community" />
      </div>
      <section className="introduction">
        <h1>About HomeLink</h1>
        <p>
          HomeLink is dedicated to bridging the gap between unhoused individuals
          and the essential resources they need. Our platform serves as a
          comprehensive guide to shelters, food services, and other critical
          support mechanisms.
        </p>
      </section>

      <section className="mission">
        <h2>Our Mission</h2>
        <p>
          Our mission is to empower those in need by providing accessible
          information that connects them with local shelters, food banks, health
          services, and community support. We believe that everyone deserves
          access to basic necessities and the support to improve their
          circumstances.
        </p>
      </section>

      <section className="how-it-works">
        <h2>How It Works</h2>
        <ol>
          <li>
            Search for resources based on your current location or specific
            needs.
          </li>
          <li>
            View detailed information about each service, including operating
            hours, services offered, and contact information.
          </li>
          <li>
            Access maps and directions to easily navigate to the resources you
            need.
          </li>
        </ol>
      </section>
    </div>
  );
};

export default About;
