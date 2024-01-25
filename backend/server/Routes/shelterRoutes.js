// shelterRoutes.js
const express = require('express');
const Shelter = require('../Models/Shelter');

const router = express.Router();

// Get all shelters
router.get('/Shelter', async (req, res) => {
  try {
    const shelter = await Shelter.findAll();
    res.json(shelter);
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'Internal Server Error' });
  }
});

// Add more routes as needed (e.g., create, update, delete)

module.exports = router;
