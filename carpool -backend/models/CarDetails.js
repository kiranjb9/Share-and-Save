var mongoose = require('mongoose');

console.log('entered register');
let CarDetails = mongoose.Schema({  
    carCompany : String,
    model : String,
    carRegisterNo: {type : String,required : true},
    licenceNo: {type : String,required : true},
}) ;



let car_details = module.exports = mongoose.model('car_details', CarDetails);
