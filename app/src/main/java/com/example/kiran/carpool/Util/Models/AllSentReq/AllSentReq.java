package com.example.kiran.carpool.Util.Models.AllSentReq;


import com.example.kiran.carpool.Util.Models.User;

public class AllSentReq {
    private String _id;
    private Sent s;

    public Sent getS() {
        return s;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setS(Sent s) {
        this.s = s;
    }
}
