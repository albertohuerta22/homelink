import axios from 'axios';

// Assuming your api instance is created like this
const api = axios.create({
  baseURL: `${process.env.REACT_APP_BACKEND_URL}`,
});

// Updated getWeather function using Axios with default parameters
export const getWeather = async (lat = '40.7128', lon = '-74.0060') => {
  try {
    // Using Axios to make a GET request with query parameters for latitude and longitude
    const response = await api.get(`/weather?latitude=${lat}&longitude=${lon}`);
    return response.data; // Assuming you want to return just the data from the Axios response
  } catch (error) {
    console.error('Error fetching weather', error);
    throw error;
  }
};
