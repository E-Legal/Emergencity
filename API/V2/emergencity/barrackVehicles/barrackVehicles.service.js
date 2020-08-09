const config = require('config.json');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const db = require('_helpers/db');
const async = require("async");
const BarrackVehicles = db.BarrackVehicles;
const Barrack = db.Barrack;
const Vehicle = db.Vehicle;

module.exports = {
    getAll,
    getById,
    getByIdVehicle,
    getVehicle,
    createBarrackVehicles,
    delete: _delete,
};

async function getAll() {
    return await BarrackVehicles.find();
}

async function getById(id) {
    return await BarrackVehicles.findById(id);
}

async function getByIdVehicle(id) {
    var test = await BarrackVehicles.find({vehicle_id: id});
    var store = [];
    test.forEach(function (doc) {
        store.push(doc.barrack_id);
    });
    return await Barrack.find({ _id: {$in : store}});
}

async function getVehicle(id) {
    var test = await BarrackVehicles.find({barrack_id: id});
    var store = [];
    test.forEach(function (doc) {
        store.push(doc.vehicle_id);
    });
    return await Vehicle.find({ _id: {$in : store}});
}

async function createBarrackVehicles(req) {
    const vehicle = new BarrackVehicles(req.body)

    return await vehicle.save();
}

async function _delete(id) {
    await BarrackVehicles.findByIdAndRemove(id);
}