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

router.put('/Shelter/:id', async (req, res) => {
  const shelterId = req.params.id;
  const { name, capacity, location } = req.body;

  try {
    const updateQuery = `
      UPDATE Shelter
      SET name = :name, capacity = :capacity, location = :location
      WHERE id = :id
    `;

    // Using Sequelize query method for raw SQL
    const [updatedRows, updatedData] = await Shelter.sequelize.query(
      updateQuery,
      {
        replacements: { id: shelterId, name, capacity, location },
        type: Shelter.sequelize.QueryTypes.UPDATE,
      }
    );

    res
      .status(200)
      .json({
        message: 'Shelter updated successfully',
        updatedRows,
        updatedData,
      });
  } catch (error) {
    console.error(error);
    res
      .status(500)
      .json({ message: 'Internal Server Error, could not update' });
  }
});

module.exports = router;
