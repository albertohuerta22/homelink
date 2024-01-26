// seed.js
const Shelter = require('./server/Models/Shelter');
const sequelize = require('./server/db'); // Adjust the path based on your project structure

// Define seed data
const sheltersSeedData = [
  { name: 'Shelter A', capacity: 50, location: 'Location A' },
  { name: 'Shelter B', capacity: 30, location: 'Location B' },
  // Add more data as needed
];

// Test if the app is connected to the database
async function testDatabaseConnection() {
  try {
    await sequelize.authenticate();
    console.log('Database connection has been established successfully.');
  } catch (error) {
    console.error('Unable to connect to the database:', error);
    process.exit(1); // Exit the script with an error code
  }
}

// Run the test and seed the database
async function seedDatabase() {
  try {
    await testDatabaseConnection();

    await sequelize.sync({ force: true }); // This will drop existing tables and recreate them

    await Shelter.bulkCreate(sheltersSeedData);

    console.log('Database seeded successfully');
  } catch (error) {
    console.error('Error seeding database:', error);
  } finally {
    await sequelize.close(); // Close the database connection
  }
}

seedDatabase();
