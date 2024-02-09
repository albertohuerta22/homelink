import React, { useState, useEffect } from 'react';

//leaflet imports
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import markerIcon from 'leaflet/dist/images/marker-icon.png';
import markerIconShadow from 'leaflet/dist/images/marker-shadow.png';

//local imports
import { getShelters } from '../Services/shelterApi/shelterApi';
import { getChargers } from '../Services/chargerApi/chargerApi';

const chargerIconUrl = 'public/assets/icons/chargerIcon.png';

// Define the default icon
const defaultIcon = L.icon({
  iconUrl: markerIcon,
  shadowUrl: markerIconShadow,
  iconSize: [25, 41], // size of the icon
  iconAnchor: [12, 41], // point of the icon which will correspond to marker's location
  popupAnchor: [1, -34], // point from which the popup should open relative to the iconAnchor
  shadowSize: [41, 41], // size of the shadow
});

const chargerIcon = L.icon({
  iconUrl: chargerIconUrl,
  iconSize: [30, 30], // size of the icon
  iconAnchor: [15, 30], // point of the icon which will correspond to marker's location
  popupAnchor: [0, -30], // point from which the popup should open relative to the iconAnchor
});

// Set the default icon for all Leaflet markers
L.Marker.prototype.options.icon = defaultIcon;

const MapComponent = () => {
  const [shelters, setShelters] = useState([]);
  const [chargers, setChargers] = useState([]);
  const center = [40.7128, -74.006]; // Central point for the map

  useEffect(() => {
    const fetchLocations = async () => {
      try {
        const sheltersCacheKey = 'sheltersData';
        const chargersCacheKey = 'chargersData';

        const cachedShelters = localStorage.getItem(sheltersCacheKey);
        const cachedChargers = localStorage.getItem(chargersCacheKey);

        let sheltersData = cachedShelters ? JSON.parse(cachedShelters) : null;
        let chargersData = cachedChargers ? JSON.parse(cachedChargers) : null;

        // If either shelters or chargers data is not cached, fetch them
        if (!sheltersData || !chargersData) {
          const [sheltersResponse, chargersResponse] = await Promise.all([
            getShelters(),
            getChargers(),
          ]);

          if (!sheltersData) {
            sheltersData = sheltersResponse;
            localStorage.setItem(
              sheltersCacheKey,
              JSON.stringify(sheltersData)
            );
          }

          if (!chargersData) {
            chargersData = chargersResponse;
            localStorage.setItem(
              chargersCacheKey,
              JSON.stringify(chargersData)
            );
          }
        }

        setShelters(sheltersData);
        setChargers(chargersData);
      } catch (error) {
        console.error('Failed to fetch locations:', error);
      }
    };

    fetchLocations();
  }, []);

  return (
    <MapContainer
      center={center}
      zoom={13}
      style={{ height: '400px', width: '100%' }}
    >
      <TileLayer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
      />
      {shelters.map((shelter) => (
        <Marker
          key={shelter.id}
          position={[shelter.latitude, shelter.longitude]}
        >
          <Popup>
            <a
              href={`https://www.google.com/maps/dir/?api=1&destination=${shelter.latitude},${shelter.longitude}`}
              target="_blank"
              rel="noopener noreferrer"
            >
              {shelter.centerName} - Open in Google Maps
            </a>
          </Popup>
        </Marker>
      ))}
      {chargers.map((charger) => (
        <Marker
          key={charger.id}
          position={[charger.latitude, charger.longitude]}
          icon={chargerIcon}
        >
          <Popup>{charger.name}</Popup>
        </Marker>
      ))}
    </MapContainer>
  );
};

export default MapComponent;
