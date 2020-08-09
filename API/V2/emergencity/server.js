require('rootpath')();
const express = require('express');
const app = express();
const cors = require('cors');
const bodyParser = require('body-parser');
const jwt = require('_helpers/jwt');
const errorHandler = require('_helpers/error-handler');

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(cors());

// use JWT auth to secure the api
app.use(jwt());

// api routes
app.use('/users', require('./users/users.controller'));
app.use('/trafficlights', require('./trafficlights/trafficlights.controller'));
app.use('/barracks', require('./barracks/barracks.controller'));
app.use('/vehicles', require('./vehicles/vehicles.controller'));
app.use('/profiles', require('./profiles/profiles.controller'));
app.use('/barrackUsers', require('./barrackUsers/barrackUsers.controller'));
app.use('/barrackVehicles', require('./barrackVehicles/barrackVehicles.controller'));
app.use('/courses', require('./courses/course.controller'));


// global error handler
app.use(errorHandler);

// start server
const port = process.env.NODE_ENV === 'production' ? (process.env.PORT || 80) : 9000;
const server = app.listen(port, function () {
    console.log('Server listening on port ' + port);
});