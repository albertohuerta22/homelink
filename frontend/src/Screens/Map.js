import React, { useState, useEffect } from 'react';

//leaflet imports
import { MapContainer, TileLayer, Marker, Popup, useMap } from 'react-leaflet';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import 'leaflet.markercluster/dist/leaflet.markercluster';
import 'leaflet.markercluster/dist/MarkerCluster.css';
import 'leaflet.markercluster/dist/MarkerCluster.Default.css';

//leaflet assets
import markerIcon from 'leaflet/dist/images/marker-icon.png';
import markerIconShadow from 'leaflet/dist/images/marker-shadow.png';

// Import your local services
import { getShelters } from '../Services/shelterApi/shelterApi';
import { getChargers } from '../Services/chargerApi/chargerApi';

const chargerIconUrl = '../../public/assets/icons/chargerIcon.png';

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
  // shadowUrl: markerIconShadow,
  iconSize: [25, 41], // size of the icon
  iconAnchor: [12, 41], // point of the icon which will correspond to marker's location
  popupAnchor: [1, -34], // point from which the popup should open relative to the iconAnchor
  shadowSize: [41, 41], // size of the shadow
});

// Set the default icon for all Leaflet markers
L.Marker.prototype.options.icon = defaultIcon;

const ChargerMarkers = ({ chargers }) => {
  const map = useMap();
  useEffect(() => {
    const markers = L.markerClusterGroup();
    chargers.forEach((charger) => {
      const marker = L.marker([charger.latitude, charger.longitude], {
        icon: chargerIcon,
      }).bindPopup(`${charger.name}`);
      markers.addLayer(marker);
    });
    map.addLayer(markers);
    return () => {
      map.removeLayer(markers);
    };
  }, [chargers, map]);

  return null;
};

const MapComponent = () => {
  const [shelters, setShelters] = useState([]);
  const [chargers, setChargers] = useState([]);
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
          <Popup>{shelter.centerName} - Open in Google Maps</Popup>
        </Marker>
      ))}
      <ChargerMarkers chargers={chargers} />
    </MapContainer>
  );
};

export default MapComponent;
