package com.example.kiran.carpool.Util.Models;

public class RiderPosts {

    private String source;
    private String destination;
    private String soure_latlong;
    private String dest_latlong;
    private String date;
    private String time;
    private String seats;
    private String preference;
    private String _id;
    private  String userid;
    private User Ride_postedBy;

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

    public String getSoure_latlong() {
        return soure_latlong;
    }

    public void setSoure_latlong(String soure_latlong) {
        this.soure_latlong = soure_latlong;
    }

    public String getDest_latlong() {
        return dest_latlong;
    }

    public void setDest_latlong(String dest_latlong) {
        this.dest_latlong = dest_latlong;
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

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }


}
