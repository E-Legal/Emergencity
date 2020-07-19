const express = require('express');
const router = express.Router();

const barrackService = require('./barrack.service');
const userService = require('../users/user.service');

// routes
router.post('/', createBarrack);
router.get('/', getAll);
router.get('/:id', getById);
router.put('/:id', updateBarrack);
router.delete('/:id', _delete);

module.exports = router;

function createBarrack(req, res, next) {
    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            barrackService.createBarrack(req)
                .then(task => res.json(task))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    })) ;
}

function getAll(req, res, next) {
    barrackService.getAll()
        .then(barracks => res.json(barracks))
        .catch(err => next(err));
}

function getById(req, res, next) {
    barrackService.getById(req.params.id)
        .then(barrack => barrack ? res.json(barrack) : res.sendStatus(404))
        .catch(err => next(err));
}

function updateBarrack(req, res, next) {
    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            barrackService.updateBarrack(req, req.params.id)
                .then(barrack => barrack ? res.json(barrack) : res.status(404).send({message: 'Task not found'}))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    })) ;
}

function _delete(req, res, next) {
    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            barrackService.delete(req.params.id)
                .then(() => res.json({}))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    })) ;
}