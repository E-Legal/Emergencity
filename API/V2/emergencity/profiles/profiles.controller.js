const express = require('express');
const router = express.Router();

const userService = require('../users/user.service');
const profileService = require('./profile.service');


// routes
router.post('/', createProfile);
router.get('/', getAll);
router.get('/me', getMe);
router.get('/:id', getById);
router.get('/user/:id', getByUserId);
router.put('/:id', updateProfileByMe);
router.put('/:id', updateProfile);
router.delete('/:id', _delete);

module.exports = router;

function createProfile(req, res, next) {
    console.log("Test");
    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            profileService.createProfile(req)
                .then(task => res.json(task))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    })) ;
}

function getAll(req, res, next) {
    profileService.getAll()
        .then(profiles => res.json(profiles))
        .catch(err => next(err));
}

function getById(req, res, next) {
    profileService.getById(req.params.id)
        .then(profile => profile ? res.json(profile) : res.status(404).send({message: 'Profile not found'}))
        .catch(err => next(err));
}

function getByUserId(req, res, next) {
    profileService.getByUserId(req.params.id)
        .then(profile => profile ? res.json(profile) : res.status(404).send({message: 'Profile not found'}))
        .catch(err => next(err));
}

function getMe(req, res, next) {
    console.log(req.user.sub)
    profileService.getByUserId(req.user.sub)
        .then(profile => profile ? res.json(profile) : res.status(404).send({message: 'Profile not found'}))
        .catch(err => next(err));
}

function updateProfileByMe(req, res, next) {
    profileService.updateProfileByMe(req, req.user.sub)
        .then(profile => profile ? res.json(profile) : res.status(404).send({message: 'Profile not found'}))
        .catch(err => next(err));
}

function updateProfile(req, res, next) {
    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            profileService.updateProfile(req, req.params.id)
                .then(profile => profile ? res.json(profile) : res.status(404).send({message: 'Profile not found'}))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    }));
}

function _delete(req, res, next) {
    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            profileService.delete(req.params.id)
                .then(() => res.json({}))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    })) ;
}