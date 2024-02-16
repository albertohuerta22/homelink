import React, { useState, useEffect } from 'react';
import { getWeather } from '../../Services/weatherApi/weatherApi';
import './Weather.css'; // Ensure this CSS file exists for styling

const Weather = () => {
  const [currentMax, setCurrentMax] = useState('');
  const [currentMin, setCurrentMin] = useState('');
  const [weatherIcon, setWeatherIcon] = useState('sun'); // Default to 'sun' icon

  useEffect(() => {
    // Define updateWeatherState inside useEffect
    const updateWeatherState = (data) => {
      setCurrentMax(convertCelsiusToFahrenheit(data.temperatureMax));
      setCurrentMin(convertCelsiusToFahrenheit(data.temperatureMin));
      setWeatherIcon(determineWeatherIcon(data.weatherCodeMax)); // Set the icon based on weather code
    };

    const fetchData = async () => {
      const cacheKey = 'weatherData';
      const cachedData = localStorage.getItem(cacheKey);
      let weatherData;

      if (cachedData) {
        const parsedData = JSON.parse(cachedData);
        const isCacheValid =
          new Date().getTime() - parsedData.timestamp < 3600000;

        if (isCacheValid) {
          weatherData = parsedData.data;
          updateWeatherState(weatherData);
          return;
        }
      }

      try {
        const response = await getWeather();
        const dailyArray = response.data.timelines.daily;
        const firstDayData = dailyArray[0];
        const values = firstDayData.values;
        weatherData = {
          temperatureMax: values.temperatureMax,
          temperatureMin: values.temperatureMin,
          weatherCodeMax: values.weatherCodeMax, // Assuming this value determines the weather condition
        };

        localStorage.setItem(
          cacheKey,
          JSON.stringify({
            timestamp: new Date().getTime(),
            data: weatherData,
          })
        );

        updateWeatherState(weatherData);
      } catch (error) {
        console.log('Error fetching data', error);
      }
    };

    fetchData();
  }, []); // No dependencies required as everything used is defined within useEffect

  function determineWeatherIcon(weatherCode) {
    switch (weatherCode) {
      case 1000: // Example code for clear weather
        return 'sun';
      case 1100: // Example code for cloudy weather
        return 'cloud';
      case 4000: // Example code for rainy weather
        return 'cloud-rain';
      case 5000: // Example code for snowy weather
        return 'cloud-snow';
      default:
        return 'sun';
    }
  }

  function convertCelsiusToFahrenheit(celsius) {
    return Math.round((celsius * 9) / 5 + 32);
  }

  return (
    <div className="weather-widget">
      <i className={`bi bi-${weatherIcon}`} style={{ fontSize: '2rem' }}></i>
      <div className="weather-info">
        <span>High: {currentMax}°F</span>
        <span>Low: {currentMin}°F</span>
      </div>
    </div>
  );
};

export default Weather;
