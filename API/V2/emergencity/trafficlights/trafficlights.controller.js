const express = require('express');
const router = express.Router();

const taskService = require('./trafficlight.service');
const userService = require('../users/user.service');

// routes
router.post('/', createTrafficLight);
router.get('/', getAll);
router.get('/:tId', getById);
router.put('/:tId', update);
router.put('/:tId/working', working);
router.delete('/:tId', deleteById);

module.exports = router;

function createTrafficLight(req, res, next) {


    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            taskService.createTrafficLight(req)
                .then(task => res.json(task))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    })) ;
}

function getAll(req, res, next) {
    taskService.getAll()
        .then(users => res.json(users))
        .catch(err => next(err));
}

function getById(req, res, next) {
    taskService.getById(req.params.tId)
        .then(trafficlight => trafficlight ? res.json(trafficlight) : res.status(404).send({message: 'TrafficLight not found'}))
        .catch(err => next(err));
}

function update(req, res, next) {
    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            taskService.update(req, req.params.tId)
                .then(trafficlight => trafficlight ? res.json(trafficlight) : res.status(404).send({message: 'TrafficLight not found'}))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    })) ;
}

function working(req, res, next) {
    taskService.updateEnd(req.params.tId)
        .then(trafficlight => trafficlight ? res.json(trafficlight) : res.sendStatus(404))
        .catch(err => next(err));
}

function deleteById(req, res, next) {
    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            taskService.deleteTrafficLight(req.params.tId)
                .then(() => res.json({}))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    })) ;
}