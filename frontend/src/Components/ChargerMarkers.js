import { useEffect } from 'react';

import { useMap } from 'react-leaflet';
import { shelterIcon, chargerIcon } from '../Components/Icons';
import L from 'leaflet';

// Set the default icon for all Leaflet markers
L.Marker.prototype.options.icon = shelterIcon;

export const ChargerMarkers = ({ chargers }) => {
  const map = useMap();
  useEffect(() => {
    const markers = L.markerClusterGroup();
    chargers.forEach((charger) => {
      const marker = L.marker([charger.latitude, charger.longitude], {
        icon: chargerIcon,
      }).bindPopup(`
        <b>${charger.streetAddress}</b><br>
        <a href="https://www.google.com/maps/dir/?api=1&destination=${charger.latitude},${charger.longitude}" target="_blank" rel="noopener noreferrer">
          Navigate to Charger
        </a>
      `);
      markers.addLayer(marker);
    });
    map.addLayer(markers);
    return () => {
      map.removeLayer(markers);
    };
  }, [chargers, map]);

  return null;
};
