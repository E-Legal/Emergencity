const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const schemaTrafficLight = new Schema({
    city: { type: String, required: true },
    x: { type: Number, required: true },
    y: { type: Number, required: true },
    z: { type: Number, required: true },
    working: {type : Boolean, default: false}
});

schemaTrafficLight.set('toJSON', {
    virtuals: true,
    versionKey: false,
    transform: function (doc, ret) {
        delete ret._id
    }
});

module.exports = mongoose.model('TrafficLight', schemaTrafficLight);