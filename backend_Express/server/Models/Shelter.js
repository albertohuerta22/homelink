// Shelter.js
const { DataTypes } = require('sequelize');
const sequelize = require('../db');

const Shelter = sequelize.define(
  'Shelter',
  {
    name: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    capacity: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    location: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    // Add more fields as needed
  },
  { tableName: 'Shelter' } // Make sure the table name is correct case
);

module.exports = Shelter;
