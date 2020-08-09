const express = require('express');
const router = express.Router();

const courseService = require('./course.service');
const userService = require('../users/user.service');

// routes
router.post('/', createCourse);
router.get('/', getAll);
router.get('/:id', getById);
router.put('/:id', updateCourse);
router.delete('/:id', _delete);

module.exports = router;

function createCourse(req, res, next) {

    courseService.createCourse(req)
        .then(task => res.json(task))
        .catch(err => next(err));
}

function getAll(req, res, next) {
    courseService.getAll()
        .then(barracks => res.json(barracks))
        .catch(err => next(err));
}

function getById(req, res, next) {
    courseService.getById(req.params.id)
        .then(barrack => barrack ? res.json(barrack) : res.sendStatus(404))
        .catch(err => next(err));
}

function updateCourse(req, res, next) {
    if (userService.is_admin(req.user.sub).then(admin => {
        if (admin == true) {
            courseService.updateCourse(req, req.params.id)
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
            courseService.delete(req.params.id)
                .then(() => res.json({}))
                .catch(err => next(err));
        } else {
            res.status(401).send({message: 'Make your dream'})
        }
    })) ;
}