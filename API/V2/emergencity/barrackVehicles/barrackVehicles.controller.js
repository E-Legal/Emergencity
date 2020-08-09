const express = require('express');
const router = express.Router();

const barrackVehiclesService = require('./barrackVehicles.service');
const userService = require('../users/user.service');

// routes
router.post('/', createBarrackVehicles);
router.get('/', getAll);
router.get('/:id', getById);
router.get('/vehicle/:id', getByUserId)
router.get('/barrack/:id', getUser)
router.delete('/:id', _delete);

module.exports = router;

function createBarrackVehicles(req, res, next) {
    console.log("Test");
    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            barrackVehiclesService.createBarrackVehicles(req)
                .then(task => res.json(task))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    })) ;
}

function getAll(req, res, next) {
    barrackVehiclesService.getAll()
        .then(barracks => res.json(barracks))
        .catch(err => next(err));
}

function getByUserId(req, res, next) {
    barrackVehiclesService.getByIdVehicle(req.params.id)
        .then(barrack => barrack ? res.json(barrack) : res.sendStatus(404))
        .catch(err => next(err));
}

function getUser(req, res, next){
    barrackVehiclesService.getVehicle(req.params.id)
        .then(barrack => barrack ? res.json(barrack) : res.sendStatus(404))
        .catch(err => next(err));
}

function getById(req, res, next) {
    barrackVehiclesService.getById(req.params.id)
        .then(barrack => barrack ? res.json(barrack) : res.sendStatus(404))
        .catch(err => next(err));
}

function _delete(req, res, next) {
    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            barrackVehiclesService.delete(req.params.id)
                .then(() => res.json({}))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    })) ;
}