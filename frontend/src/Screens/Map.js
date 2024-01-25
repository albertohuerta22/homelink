import React from 'react';
import { GoogleMap, LoadScript } from '@react-google-maps/api';

const Map = () => {
  const mapContainerStyle = {
    height: '400px',
    width: '100%',
  };

  const center = {
    lat: 40.7128,
    lng: -74.006,
  };

  return (
    <LoadScript
      googleMapsApiKey={process.env.REACT_APP_GOOGLE_MAPS_API_KEY}
      libraries={['places']} // Add additional libraries as needed
    >
      <GoogleMap
        mapContainerStyle={mapContainerStyle}
        center={center}
        zoom={12} //smaller is out; bigger is closer
      ></GoogleMap>
    </LoadScript>
  );
};

export default Map;
