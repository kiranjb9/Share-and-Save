package com.example.kiran.carpool.Util.Models;

public class RiderPosts {

    private String source;
    private String destination;
    private String sourceName;
    private String destinationName;

    private String soure_lat;
    private String soure_long;
    private String dest_lat;
    private String dest_long;


    private String date;
    private String time;
    private String seats;
    private String _id;
    private  String userid;
    private User Ride_postedBy;

    private String amount;
    private String distance;
    private String duration;

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public User getRide_postedBy() {
        return Ride_postedBy;
    }

    public void setRide_postedBy(User ride_postedBy) {
        Ride_postedBy = ride_postedBy;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSoure_lat() {
        return soure_lat;
    }

    public void setSoure_lat(String soure_lat) {
        this.soure_lat = soure_lat;
    }

    public String getSoure_long() {
        return soure_long;
    }

    public void setSoure_long(String soure_long) {
        this.soure_long = soure_long;
    }

    public String getDest_lat() {
        return dest_lat;
    }

    public void setDest_lat(String dest_lat) {
        this.dest_lat = dest_lat;
    }

    public String getDest_long() {
        return dest_long;
    }

    public void setDest_long(String dest_long) {
        this.dest_long = dest_long;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
