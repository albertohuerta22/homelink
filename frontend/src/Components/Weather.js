import React, { useState, useEffect } from 'react';
import { getWeather } from '../Services/weatherApi/weatherApi';

const Weather = () => {
  const [currentMax, setCurrentMax] = useState('');
  const [currentMin, setCurrentMin] = useState('');

  useEffect(() => {
    const fetchData = async () => {
      // Define a unique key for the weather data cache
      const cacheKey = 'weatherData';
      // Try to get cached weather data
      const cachedData = localStorage.getItem(cacheKey);
      let weatherData;

      if (cachedData) {
        // Parse the cached JSON data
        const parsedData = JSON.parse(cachedData);
        // Check if the cache is still valid, assuming 1 hour validity
        const isCacheValid =
          new Date().getTime() - parsedData.timestamp < 3600000;

        if (isCacheValid) {
          weatherData = parsedData.data;
        }
      }

      if (!weatherData) {
        try {
          const response = await getWeather();
          const dailyArray = response.data.timelines.daily;
          const firstDayData = dailyArray[0];
          const values = firstDayData.values;
          weatherData = {
            temperatureMax: values.temperatureMax,
            temperatureMin: values.temperatureMin,
          };

          // Store the new data with a timestamp
          localStorage.setItem(
            cacheKey,
            JSON.stringify({
              timestamp: new Date().getTime(),
              data: weatherData,
            })
          );
        } catch (error) {
          console.log('Error fetching data', error);
          return;
        }
      }

      // Set state with either cached or fetched data
      setCurrentMax(convertCelsiusToFahrenheit(weatherData.temperatureMax));
      setCurrentMin(convertCelsiusToFahrenheit(weatherData.temperatureMin));
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
