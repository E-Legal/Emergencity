const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const schema = new Schema({
    address: { type: String, required: true },
    long_pos: { type: String, required: true },
    long_des: { type: String },
    lat_pos: { type: String, required: true },
    lat_des: { type: String },
    course: { type: String },
    time: { type: String },
    distance: { type: String },
});

schema.set('toJSON', {
    virtuals: true,
    versionKey: false,
    transform: function (doc, ret) {
        delete ret._id;
    }
});

module.exports = mongoose.model('Course', schema);