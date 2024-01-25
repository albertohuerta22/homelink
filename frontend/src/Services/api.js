// services/api.js
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:3001/api',
});

export const getShelters = async () => {
  try {
    const response = await api.get('/shelter');
    return response.data;
  } catch (error) {
    console.error('Error fetching shelters', error);
    throw error;
  }
};

// Add more API functions as needed
