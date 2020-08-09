const config = require('config.json');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const db = require('_helpers/db');
const Courses = db.Course;

module.exports = {
    getAll,
    getById,
    createCourse,
    updateCourse,
    delete: _delete,
};

async function getAll() {
    return await Courses.find();
}

async function getById(id) {
    return await Courses.findById(id);
}

async function createCourse(req) {
    const course = new Courses(req.body)

    let https = require ('https');
    let host = 'https://api-adresse.data.gouv.fr';
    let path = '/search';
    let mkt = 'en-US';
    let query = "?q=" + encodeURI(course.address);

    let request_params = host + path + query;

    console.log(request_params);


    const axios = require('axios');

    let coordinates = await axios.get(request_params)
        .then(response => {
            if (response.data.features == null) throw 'Barrack not found';
            let value = response.data.features[0].geometry.coordinates;
            return value;
        })
        .catch(error => {
            console.log(error);
        });
    console.log(coordinates);
    course.long_des = coordinates[0].toString();
    course.lat_des = coordinates[1].toString();
    console.log(course.lat_des);
    return await course.save();
}

async function updateCourse(req, id) {
    const course = await Courses.findById(id);
    // validate
    if (!course) throw 'Course not found';

    Object.assign(course, req.body);
    console.log(course);
    return course.save();
}


async function _delete(id) {
    await Courses.findByIdAndRemove(id);
}