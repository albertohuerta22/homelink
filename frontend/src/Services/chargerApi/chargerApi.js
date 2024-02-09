import axios from 'axios';

const api = axios.create({
  baseURL: `${process.env.REACT_APP_BACKEND_URL}`,
});

export const getChargers = async () => {
  try {
    const response = await api.get('/chargers');
    console.log(response);
    return response.data;
  } catch (error) {
    console.error('Error fetching shelters', error);
    throw error;
  }
};
