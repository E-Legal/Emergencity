const express = require('express');
const router = express.Router();

const vehicleService = require('./vehicle.service');
const userService = require('../users/user.service');

// routes
router.post('/', createVehicle);
router.get('/', getAll);
router.get('/:id', getById);
router.put('/:id', updateVehicle);
router.delete('/:id', _delete);

module.exports = router;

function createVehicle(req, res, next) {
    console.log("Test");
    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            vehicleService.createVehicle(req)
                .then(task => res.json(task))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    })) ;
}

function getAll(req, res, next) {
    vehicleService.getAll()
        .then(barracks => res.json(barracks))
        .catch(err => next(err));
}

function getById(req, res, next) {
    vehicleService.getById(req.params.id)
        .then(barrack => barrack ? res.json(barrack) : res.sendStatus(404))
        .catch(err => next(err));
}

function updateVehicle(req, res, next) {
    console.log(req.user.sub);
    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            vehicleService.updateVehicle(req, req.params.id)
                .then(barrack => barrack ? res.json(barrack) : res.status(404).send({message: 'Task not found'}))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    }));
}

function _delete(req, res, next) {
    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            vehicleService.delete(req.params.id)
                .then(() => res.json({}))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    })) ;
}