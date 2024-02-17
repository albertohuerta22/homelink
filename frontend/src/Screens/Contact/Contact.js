import React from 'react';
import './Contact.css';
// import actionImage from '../../assets/action_image.png';

const Contact = () => {
  return (
    <div className="contact-container">
      <div className="contact-header">
        <img
          src={
            'https://images.unsplash.com/photo-1612154258609-24edbbbb2279?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'
          }
          alt="Take Action"
        />
      </div>

      <div className="contact-form">
        <h2>Contact Us</h2>
        <form>
          <input type="email" placeholder="Your Email" />
          <textarea placeholder="Your Message or Recommendation"></textarea>
          <button type="submit">Send Message</button>
        </form>
      </div>

      <div className="contribute-section">
        <h2>Contribute as a Developer</h2>
        <p>
          Are you a developer or a tech-savvy individual looking to make a
          difference? HomeLink is on a mission to empower unhoused community
          members through technology, and we need your expertise. Explore our{' '}
          <a href="https://github.com/HomeLink">GitHub repository</a> to see how
          you can contribute to our project.
        </p>
      </div>

      <div className="donate-section">
        <h2>Donate</h2>
        <p>
          Your contribution helps keep HomeLink running and supports our
          mission. <a href="https://homelinkNYC">Donate here</a>.
        </p>
      </div>
    </div>
  );
};

export default Contact;
