package com.example.kiran.carpool.Util.Models.AllSentReq;


import com.example.kiran.carpool.Util.Models.User;

import java.util.List;

public class AllSentReq {
    private String _id;
    private List<Sent> s;

    public List<Sent> getS() {
        return this.s;
    }

    public void setS(List<Sent> s) {
        this.s = s;
    }



    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
