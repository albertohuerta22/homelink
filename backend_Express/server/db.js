// db.js
const { Sequelize } = require('sequelize');

const sequelize = new Sequelize({
  dialect: 'mysql',
  host: 'localhost', // Change this to your MySQL server host
  username: 'root', // Change this to your MySQL username
  password: 'root', // Change this to your MySQL password
  database: 'homelink', // Change this to your desired database name
});

module.exports = sequelize;
