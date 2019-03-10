var mongoose = require('mongoose');
var mongooseUniqueValidator = require('mongoose-unique-validator');
console.log('entered register');
let RegisterUser = mongoose.Schema({  
    fname : String,
    lname : String,
    mobilenumber : {type : String,required : true,unique : true},
    email : {type : String,required : true,unique : true},
    pass :  {type : String, required : true},
    ride_posts: [{type : mongoose.Schema.ObjectId, ref : 'ride_offers'}],
    car : [{type : mongoose.Schema.ObjectId , ref : 'car_details'}]
}) ;

//making Email and Mobile Number unique
RegisterUser.plugin(mongooseUniqueValidator);

let reg = module.exports = mongoose.model('reg', RegisterUser);
