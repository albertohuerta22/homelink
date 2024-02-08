import React, { useState, useEffect } from 'react';
import { getWeather } from '../Services/weatherApi/weatherApi';

const Weather = () => {
  const [currentMax, setCurrentMax] = useState('');
  const [currentMin, setCurrentMin] = useState('');

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getWeather();
        const dailyArray = response.data.timelines.daily;
        const firstDayData = dailyArray[0];
        const values = firstDayData.values;
        const maxTempFahrenheit = values.temperatureMax;
        const minTempFahrenheit = values.temperatureMin;
        setCurrentMax(convertCelsiusToFahrenheit(maxTempFahrenheit));
        setCurrentMin(convertCelsiusToFahrenheit(minTempFahrenheit));
      } catch (error) {
        console.log('Error fetching data', error);
      }
    };
    fetchData();
  }, []);

  function convertCelsiusToFahrenheit(celsius) {
    return Math.round((celsius * 9) / 5 + 32);
  }

  return (
    <div>
      Hi, current weather in New York is: H {currentMax} / L {currentMin}.
    </div>
  );
};

export default Weather;
