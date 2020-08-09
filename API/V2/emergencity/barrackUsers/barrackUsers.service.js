const config = require('config.json');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const db = require('_helpers/db');
const async = require("async");
const BarrackUsers = db.BarrackUsers;
const Barrack = db.Barrack;
const User = db.User;

module.exports = {
    getAll,
    getById,
    getByIdUser,
    getUser,
    createBarrackUsers,
    delete: _delete,
};

async function getAll() {
    return await BarrackUsers.find();
}

async function getById(id) {
    return await BarrackUsers.findById(id);
}

async function getByIdUser(id) {
    var test = await BarrackUsers.find({user_id: id});
    var store = [];
    test.forEach(function (doc) {
        store.push(doc.barrack_id);
    });
    return await Barrack.find({ _id: {$in : store}});
}

async function getUser(id) {
    var test = await BarrackUsers.find({barrack_id: id});
    var store = [];
    test.forEach(function (doc) {
        store.push(doc.user_id);
    });
    return await User.find({ _id: {$in : store}});
}

async function createBarrackUsers(req) {
    const vehicle = new BarrackUsers(req.body)

    return await vehicle.save();
}

async function _delete(id) {
    await BarrackUsers.findByIdAndRemove(id);
}