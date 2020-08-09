const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const schema = new Schema({
    barrack_id: {type : Object, required: true},
    user_id: {type : Object, required: true}
});

schema.index({barrack_id: 1, user_id: 1}, {unique: true})

schema.set('toJSON', {
    virtuals: true,
    versionKey: false,
    transform: function (doc, ret) {
        delete ret._id;
    }
});

module.exports = mongoose.model('BarrackUsers', schema);