import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './Screens/Home';
import Navbar from './Components/Navbar';
import About from './Screens/About';
import Map from './Screens/Map';
import Contact from './Screens/Contact';
import Footer from './Components/Footer';

import './App.css';

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/map" element={<Map />} />
        <Route path="/contact" element={<Contact />} />
      </Routes>
      <Footer />
    </Router>
  );
}

export default App;
