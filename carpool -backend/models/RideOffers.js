var mongoose = require('mongoose');
var mongooseUniqueValidator = require('mongoose-unique-validator');
console.log('entered register');
let RideOffers = mongoose.Schema({  
    source :  {type : String,required : true,unique : true},
    destination :  {type : String,required : true,unique : true},
    sp1: String,
    sp2: String,
    sp3: String,
    sp4 : String,
    sp5 : String,

    soure_latlong : String,
    dest_latlong : String,

    sp1_latlong : String,
    sp2_latlong : String,
    sp3_latlong : String,
    sp4_latlong : String,
    sp5_latlong : String,

    seats : String,

    preference : String,
   
    date :  {type : String,required : true,unique : true},
    time :  {type : String,required : true,unique : true},

    Ride_postedBy:{type : mongoose.Schema.ObjectId, ref : 'reg'}
}) ;

//making Email and Mobile Number unique

RideOffers.plugin(mongooseUniqueValidator);
let ride_offers = module.exports = mongoose.model('ride_offers', RideOffers);
