import React from 'react';
import './Layout.css'; // Path to your CSS file for layout styles

const Layout = ({ children }) => {
  return <div className="main-layout">{children}</div>;
};

export default Layout;
