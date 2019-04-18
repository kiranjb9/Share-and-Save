package com.example.kiran.carpool.Util.Models.AllSentReq;


import com.example.kiran.carpool.Util.Models.User;

import java.util.List;

public class AllSentReq {
    private String seats_requested;
    private String post_id;
    private String source;
    private String destination;

    private String sent_for_user_id;

    private String fname;

    private String lname;
    private String email;

    private String mobilenumber;

    public String getSeats_requested() {
        return seats_requested;
    }

    public void setSeats_requested(String seats_requested) {
        this.seats_requested = seats_requested;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
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

    public String getSent_for_user_id() {
        return sent_for_user_id;
    }

    public void setSent_for_user_id(String sent_for_user_id) {
        this.sent_for_user_id = sent_for_user_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }
}
