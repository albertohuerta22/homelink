import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './Screens/Home/Home';
import Navbar from './Components/Navbar/Navbar';
import About from './Screens/About';
import Map from './Screens/Map/Map';
import Contact from './Screens/Contact';
import Footer from './Components/Footer/Footer';
import NotFound from './Screens/NotFound/NotFound';
import Layout from './Layout/Layout';
import './App.css';

function App() {
  return (
    <Router>
      <Navbar />
      <Layout>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/home" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/map" element={<Map />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </Layout>

      <Footer />
    </Router>
  );
}

export default App;
