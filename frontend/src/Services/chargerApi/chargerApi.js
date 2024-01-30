import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8081',
});

export const getShelters = async () => {
  try {
    const response = await api.get('/chargers');
    console.log(response);
    return response.data;
  } catch (error) {
    console.error('Error fetching shelters', error);
    throw error;
  }
};
