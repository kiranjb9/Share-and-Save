var express= require('express');
var router = express.Router();
var mongoose = require('mongoose');
var bodyParser = require('body-parser'); 
mongoose.connect("mongodb://localhost:27017/carpool", { useNewUrlParser: true });
let reg = require('./models/RegisterUser');
let car_details = require('./models/CarDetails');
let ride_offers =  require('./models/RideOffers')
 var db = mongoose.connection;

db.on("error", console.error.bind(console, "connection error"));
 db.once("open", function(callback) {
     console.log("Connection succeeded.");
  });
 const app = express();
 app.use(bodyParser.urlencoded({
     extended: true
 }));
 app.use(bodyParser.json());

 // Bring in Models
 let regis = new reg();
 let cd = new car_details();
 let rd = new ride_offers();

app.post('/insertUserData',function(req , res){
    let regis = new reg();
    regis.fname = req.body.fname;
    regis.lname = req.body.lname;
    regis.mobilenumber = req.body.mobilenumber;
    regis.email = req.body.email;
    regis.pass = req.body.pass;

    regis.save(function(err){
        
    
        if(err){
            res.send("ERROR :" + err);    
        }
        else{
                res.send("1"); 
            
        }
    });
   

});

// Login TO DB==================================================================
app.post('/login',function(req,res){
    reg.findOne({email : req.body.email} , function(err,user){
        console.log(user)
        if (err) throw err;
        if(user == null){
        return res.status(404).end('Wrong Email........');
        }
        else if(req.body.pass === user.pass){
            res.send("1");
        }
        else{
            return res.status(400).end('PASSWORD IS WRONG');
        }          
    });  
});

//CarDetails entry
app.post('/insertCarDetails',function(req , res){
    let cd = new car_details();

    cd.carCompany = req.body.carCompany;
    cd.model = req.body.model;
    cd.carRegisterNo = req.body.carRegisterNo;
    cd.licenceNo = req.body.licenceNo;
  
    cd.save(function(err){
        if(err){
            res.send("ERROR :" + err);    
        }
        else{
                res.send(cd);            
        }
    });
   

});

app.post('/insertRideOffers',function(req , res){
    let rd = new ride_offers();
    rd.source = req.body.source;
    rd.destination = req.body.destination;

    rd.sp1 = req.body.sp1;
    rd.sp2 = req.body.sp2;
    rd.sp3 = req.body.sp3;
    rd.sp4 = req.body.sp4;
    rd.sp5 = req.body.sp5;

    rd.soure_latlong = req.body.soure_latlong;
    rd.dest_latlong = req.body.dest_latlong;

    rd.seats= req.body.seats
    rd.preferece = req.body.preference

    rd.sp1_latlong = req.body.sp1_latlong;
    rd.sp2_latlong = req.body.sp2_latlong;
    rd.sp3_latlong = req.body.sp3_latlong;
    rd.sp4_latlong = req.body.sp4_latlong;
    rd.sp5_latlong = req.body.sp5_latlong;

    rd.date = req.body.date;
    rd.time = req.body.time;
  
    rd.save(function(err){
        if(err){
            res.send("ERROR :" + err);    
        }
        else{
                res.send("1");            
        }
    });
   

});

app.listen(3000, function(){
    console.log('Server started on port 3000...');
  });