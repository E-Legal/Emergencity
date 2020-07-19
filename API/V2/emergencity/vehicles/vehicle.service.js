const config = require('config.json');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const db = require('_helpers/db');
const Vehicle = db.Vehicle;

module.exports = {
    getAll,
    getById,
    createVehicle,
    updateVehicle,
    delete: _delete,
};

async function getAll() {
    return await Vehicle.find();
}

async function getById(id) {
    return await Vehicle.findById(id);
}

async function createVehicle(req) {
    const vehicle = new Vehicle(req.body)

    return await vehicle.save();
}

async function updateVehicle(req, id) {
    const vehicle = await Vehicle.findById(id);
    if (!vehicle) throw 'Vehicle not found';
    Object.assign(vehicle, req.body);
    return await vehicle.save();
}


async function _delete(id) {
    await Vehicle.findByIdAndRemove(id);
}