import React, { useState, useEffect } from 'react';

//leaflet imports
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';

import 'leaflet/dist/leaflet.css';
import 'leaflet.markercluster/dist/leaflet.markercluster';
import 'leaflet.markercluster/dist/MarkerCluster.css';
import 'leaflet.markercluster/dist/MarkerCluster.Default.css';

// Import your local services
import { getShelters } from '../../Services/shelterApi/shelterApi';
import { getChargers } from '../../Services/chargerApi/chargerApi';
import { ChargerMarkers } from '../../Components/ChargerMarkers';

import './Map.css';

const MapComponent = () => {
  const [shelters, setShelters] = useState([]);
  const [chargers, setChargers] = useState([]);
  // State to control shelter visibility
  const [currentView, setCurrentView] = useState('shelters');
  const center = [40.7128, -74.006]; // Central point for the map

  useEffect(() => {
    const fetchLocations = async () => {
      const sheltersCacheKey = 'sheltersData';
      const chargersCacheKey = 'chargersData';

      const cachedShelters = localStorage.getItem(sheltersCacheKey);
      const cachedChargers = localStorage.getItem(chargersCacheKey);

      let sheltersData = cachedShelters ? JSON.parse(cachedShelters) : null;
      let chargersData = cachedChargers ? JSON.parse(cachedChargers) : null;

      const promises = [];

      if (!sheltersData)
        promises.push(
          getShelters()
            .then((data) => ({ type: 'shelters', data }))
            .catch((error) => ({ type: 'shelters', error }))
        );
      if (!chargersData)
        promises.push(
          getChargers()
            .then((data) => ({ type: 'chargers', data }))
            .catch((error) => ({ type: 'chargers', error }))
        );

      const results = await Promise.allSettled(promises);

      results.forEach((result) => {
        if (result.status === 'fulfilled') {
          const { type, data } = result.value;
          if (type === 'shelters') {
            sheltersData = data;
            localStorage.setItem(sheltersCacheKey, JSON.stringify(data));
          } else if (type === 'chargers') {
            chargersData = data;
            localStorage.setItem(chargersCacheKey, JSON.stringify(data));
          }
        } else {
          console.error(
            `Failed to fetch ${result.value.type}:`,
            result.value.error
          );
        }
      });

      if (sheltersData) setShelters(sheltersData);
      if (chargersData) setChargers(chargersData);
    };

    fetchLocations();
  }, []);

  //toggle button functions
  const switchView = (view) => {
    setCurrentView(view);
  };

  return (
    <div>
      <div className="map-page-container">
        <div className="directions-container">
          <h4>How to Use</h4>
          <p>Step 1 - Do this</p>
          <p>Step 2 - Do that</p>
          <p>Step 3 - Select an option</p>
          <p>Step 4 - Click a marker</p>
          <p>Step 5 - Explore info</p>
        </div>

        <MapContainer
          center={center}
          zoom={14}
          style={{ height: '400px', width: '50%' }}
          className="map-container"
        >
          <TileLayer
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
          />
          {currentView === 'shelters' &&
            shelters.map((shelter) => (
              <Marker
                key={shelter.id}
                position={[shelter.latitude, shelter.longitude]}
              >
                <Popup>
                  {shelter.centerName} -
                  <a
                    href={`https://www.google.com/maps/search/?api=1&query=${encodeURIComponent(
                      shelter.address
                    )}`}
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    Open in Google Maps
                  </a>
                </Popup>
              </Marker>
            ))}
          {currentView === 'chargers' && <ChargerMarkers chargers={chargers} />}
        </MapContainer>

        <div className="legend-container">
          <h4>Legend</h4>
          {/* Replace these with actual icons and descriptions */}
          <div>
            <i className="icon-shelter"></i> Shelter
          </div>
          <div>
            <i className="icon-charger"></i> Charger
          </div>
        </div>
      </div>
      <div className="map-controls">
        <button
          className="map-control-button"
          onClick={() => switchView('shelters')}
          disabled={currentView === 'shelters'}
        >
          Shelters
        </button>
        <button
          className="map-control-button"
          onClick={() => switchView('chargers')}
          disabled={currentView === 'chargers'}
        >
          Chargers
        </button>
      </div>
    </div>
  );
};

export default MapComponent;
