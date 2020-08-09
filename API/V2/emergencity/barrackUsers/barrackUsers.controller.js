const express = require('express');
const router = express.Router();

const barrackUsersService = require('./barrackUsers.service');
const userService = require('../users/user.service');

// routes
router.post('/', createBarrackUsers);
router.get('/', getAll);
router.get('/:id', getById);
router.get('/user/:id', getByUserId)
router.get('/barrack/:id', getUser)
router.delete('/:id', _delete);

module.exports = router;

function createBarrackUsers(req, res, next) {
    console.log("Test");
    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            barrackUsersService.createBarrackUsers(req)
                .then(task => res.json(task))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    })) ;
}

function getAll(req, res, next) {
    barrackUsersService.getAll()
        .then(barracks => res.json(barracks))
        .catch(err => next(err));
}

function getByUserId(req, res, next) {
    barrackUsersService.getByIdUser(req.params.id)
        .then(barrack => barrack ? res.json(barrack) : res.sendStatus(404))
        .catch(err => next(err));
}

function getUser(req, res, next){
    barrackUsersService.getUser(req.params.id)
        .then(barrack => barrack ? res.json(barrack) : res.sendStatus(404))
        .catch(err => next(err));
}

function getById(req, res, next) {
    barrackUsersService.getById(req.params.id)
        .then(barrack => barrack ? res.json(barrack) : res.sendStatus(404))
        .catch(err => next(err));
}

function _delete(req, res, next) {
    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            barrackUsersService.delete(req.params.id)
                .then(() => res.json({}))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    })) ;
}