// shelterRoutes.js
const express = require('express');
const Shelter = require('../Models/Shelter');

const router = express.Router();

// Get all shelters
router.get('/shelter', async (req, res) => {
  try {
    const shelter = await Shelter.findAll();
    res.json(shelter);
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'Internal Server Error' });
  }
});

router.post('/shelter', async (req, res) => {
  try {
    const newShelter = await Shelter.create(req.body);
    res.status(201).json(newShelter);
  } catch (error) {
    console.log(error);
    res.status(500).json({ message: 'Internal Server Error, could not post' });
  }
});

//GET SINGLE SHELTER
router.get('/shelter/:id', async (req, res) => {
  const shelterId = req.params.id;

  try {
    // Find the shelter by ID
    const shelter = await Shelter.findByPk(shelterId);

    if (!shelter) {
      // Shelter not found
      return res.status(404).json({ message: 'Shelter not found' });
    }

    res.json(shelter);
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'Internal Server Error' });
  }
});

//UPDATE SINGLE SHELTER
router.put('/shelter/:id', async (req, res) => {
  const { id } = req.params;
  const { name, capacity, location } = req.body;

  try {
    const [updatedRows] = await Shelter.update(
      { name, capacity, location },
      { where: { id: shelterId } }
    );

    if (updatedRows > 0) {
      // Update was successful
      res.status(200).json({ message: 'Shelter updated successfully' });
    } else {
      // No rows were updated, shelter with the specified ID not found
      res.status(404).json({ message: 'Shelter not found' });
    }
  } catch (error) {
    console.error(error);
    res
      .status(500)
      .json({ message: 'Internal Server Error, could not update' });
  }
});

//DELETE SINGLE SHELTER
router.delete('/shelter/:id', async (req, res) => {
  const shelterId = req.params.id;

  try {
    // Find the shelter by ID
    const shelter = await Shelter.findByPk(shelterId);

    if (!shelter) {
      // Shelter not found
      return res.status(404).json({ message: 'Shelter not found' });
    }

    // Delete the shelter
    await shelter.destroy();

    res.status(200).json({ message: 'Shelter deleted successfully' });
  } catch (error) {
    console.error(error);
    res
      .status(500)
      .json({ message: 'Internal Server Error, could not delete' });
  }
});

module.exports = router;

module.exports = router;
