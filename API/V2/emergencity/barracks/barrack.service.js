const config = require('config.json');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const db = require('_helpers/db');
const Barrack = db.Barrack;

module.exports = {
    getAll,
    getById,
    createBarrack,
    updateBarrack,
    delete: _delete,
};

async function getAll() {
    return await Barrack.find();
}

async function getById(id) {
    return await Barrack.findById(id);
}

async function createBarrack(req) {
    const barrack = new Barrack(req.body)

    return await barrack.save();
}

async function updateBarrack(req, id) {
    const barrack = await Barrack.findById(id);
    if (!barrack) throw 'Barrack not found';
    Object.assign(barrack, req.body);
    return await barrack.save();
}


async function _delete(id) {
    await Barrack.findByIdAndRemove(id);
}