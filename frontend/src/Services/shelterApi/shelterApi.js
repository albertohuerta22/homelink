import axios from 'axios';

const api = axios.create({
  baseURL: `${process.env.REACT_APP_BACKEND_URL}`,
});

export const getShelters = async () => {
  try {
    const response = await api.get('/shelters');
    return response.data;
  } catch (error) {
    console.error('Error fetching shelters', error);
    throw error;
  }
};

// Add more API functions as needed
