// db.js
const { Sequelize } = require('sequelize');

const dbUsername = process.env.REACT_APP_DB_USERNAME;
const dbPassword = process.env.REACT_APP_DB_PASSWORD;
const dbHost = process.env.REACT_APP_DB_PASSWORD;

const sequelize = new Sequelize({
  dialect: 'mysql',
  host: `${dbHost}`, // Change this to your MySQL server host
  username: `${dbUsername}`, // Change this to your MySQL username
  password: `${dbPassword}`, // Change this to your MySQL password
  database: 'homelink', // Change this to your desired database name
});

module.exports = sequelize;
