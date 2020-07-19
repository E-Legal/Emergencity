const config = require('config.json');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');
const db = require('_helpers/db');
const mongoose = require('mongoose');
const Profile = db.Profile;
const User = db.User;

module.exports = {
    getAll,
    getById,
    getByUserId,
    createProfile,
    updateProfileByMe,
    updateProfile,
    delete: _delete
};

async function getAll() {
    return await Profile.find();
}

async function getById(id) {
    return await Profile.findById(id);
}

async function getByUserId(id) {
    return Profile.findOne({user_id: id});
}


async function createProfile(req) {
    const profile = new Profile(req.body)

    return await profile.save();
}

async function updateProfileByMe(req, id) {
    const profile = await Profile.findOne({user_id: id});
    if (!profile) throw 'Profile not found';
    Object.assign(profile, req.body);
    return await profile.save();
}

async function updateProfile(req, id) {
    const profile = await Profile.findById(id);
    if (!profile) throw 'Profile not found';
    Object.assign(profile, req.body);
    return await profile.save();
}


async function _delete(id) {
    await Profile.findByIdAndRemove(id);
}