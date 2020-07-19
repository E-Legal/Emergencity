const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const schema = new Schema({
    first_name: { type: String, required: true },
    last_name: { type: String, required: true },
    job: { type: String, required: true },
    user_id: {type : Object, unique: true, required: true}
});

schema.set('toJSON', {
    virtuals: true,
    versionKey: false,
    transform: function (doc, ret) {
        delete ret._id;
    }
});

module.exports = mongoose.model('Profile', schema);