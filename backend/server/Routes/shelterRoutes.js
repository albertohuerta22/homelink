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

router.post('/Shelter', async (req, res) => {
  try {
    const newShelter = await Shelter.create(req.body);
    res.status(201).json(newShelter);
  } catch (error) {
    console.log(error);
    res.status(500).json({ message: 'Internal Server Error, could not post' });
  }
});

router.put('/shelter/:id', async (req, res) => {
  const { id } = req.params;
  const { name, capacity, location } = req.body;

  try {
    const [updatedRows, _] = await Shelter.update(
      { name, capacity, location },
      { where: { id } }
    );

    if (updatedRows > 0) {
      res.status(200).json({ message: 'Shelter updated successfully' });
    } else {
      res.status(404).json({ message: 'Shelter not found' });
    }
  } catch (error) {
    console.error(error);
    res
      .status(500)
      .json({ message: 'Internal Server Error, could not update' });
  }
});

module.exports = router;
