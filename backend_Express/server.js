const express = require('express');
const cors = require('cors');
const app = express();
const Shelter = require('./server/Routes/shelterRoutes');

app.use(cors()); // Enable CORS for all routes
app.use('/api', Shelter);

const port = process.env.PORT || 3001;

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
