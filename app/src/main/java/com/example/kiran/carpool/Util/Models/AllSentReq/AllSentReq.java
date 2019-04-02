package com.example.kiran.carpool.Util.Models.AllSentReq;


import com.example.kiran.carpool.Util.Models.User;

public class AllSentReq {
    private User user;
    private Sent s;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sent getS() {
        return s;
    }

    public void setS(Sent s) {
        this.s = s;
    }
}
