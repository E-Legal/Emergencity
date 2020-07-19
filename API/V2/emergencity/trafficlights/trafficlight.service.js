const db = require('_helpers/db');
const TrafficLight = db.Trafficlight;

module.exports = {
    createTrafficLight,
    getAll,
    getById,
    update,
    updateEnd,
    deleteTrafficLight
};


async function createTrafficLight(req) {
    const task = new TrafficLight(req.body)

    return await task.save();
}

async function getAll() {
    return await TrafficLight.find();
}

async function getById(id) {
    return await TrafficLight.findById(id);
}

async function update(req, id) {
    console.log(req.body);
    const trafficlight = await TrafficLight.findById(id);
    Object.assign(trafficlight, req.body);
    return await trafficlight.save();
}

async function updateEnd(id) {
    const trafficlight = await TrafficLight.findById(id);
    if (!trafficlight) throw 'Task not found';
    const trafficlightParam = Object.create(trafficlight);


    if (trafficlight.working == false) {
        trafficlightParam.working = true;
    } else
        trafficlightParam.working = false;


    Object.assign(trafficlight, trafficlightParam);
    return await trafficlight.save();
}

async function deleteTrafficLight(id) {
    await TrafficLight.findByIdAndRemove(id);
}