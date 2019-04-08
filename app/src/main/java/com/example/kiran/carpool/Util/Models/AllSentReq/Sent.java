package com.example.kiran.carpool.Util.Models.AllSentReq;

import com.example.kiran.carpool.Util.Models.RiderPosts;
import com.example.kiran.carpool.Util.Models.User;

public class Sent {
    private RiderPosts requests_sent_for_post;
    private User requests_sent_for_the_user;
    private  String  seats_requested;
    private  String  _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public RiderPosts getRequests_sent_for_post() {
        return requests_sent_for_post;
    }

    public void setRequests_sent_for_post(RiderPosts requests_sent_for_post) {
        this.requests_sent_for_post = requests_sent_for_post;
    }

    public User getRequests_sent_for_the_user() {
        return requests_sent_for_the_user;
    }

    public void setRequests_sent_for_the_user(User requests_sent_for_the_user) {
        this.requests_sent_for_the_user = requests_sent_for_the_user;
    }

    public String getSeats_requested() {
        return seats_requested;
    }

    public void setSeats_requested(String seats_requested) {
        this.seats_requested = seats_requested;
    }

}
