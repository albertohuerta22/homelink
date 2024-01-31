// services/api.js
import axios from 'axios';

const api = axios.create({
  baseURL: `${process.env.REACT_APP_BACKEND_URL}`,
});

export const getWeather = async () => {
  try {
    const response = await api.get('/shelters');
    return response.data;
  } catch (error) {
    console.error('Error fetching shelters', error);
    throw error;
  }
};

// Add more API functions as needed
