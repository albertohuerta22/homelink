import React, { useState, useEffect } from 'react';
import { getWeather } from '../Services/weatherApi/weatherApi';

const Weather = () => {
  const [currentMax, setCurrentMax] = useState('');
  const [currentMin, setCurrentMin] = useState('');

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getWeather();

        const maxTempFahrenheit = response.maxTempFahrenheit;
        const minTempFahrenheit = response.minTempFahrenheit;
        setCurrentMax(maxTempFahrenheit);
        setCurrentMin(minTempFahrenheit);
      } catch (error) {
        console.log('Error fetching data', error);
      }
    };
    fetchData();
  }, []);

  return (
    <div>
      Hi, current weather in New York is: H {currentMax} / L {currentMin}.
    </div>
  );
};

export default Weather;
