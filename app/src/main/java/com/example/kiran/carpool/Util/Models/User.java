package com.example.kiran.carpool.Util.Models;

public class User {
    private  String _id;
    private String fname;
    private String lname;
    private String email;
    private String mobilenumber;
    private String pass;
    private RiderPosts requests_sent_for_post;
    private  String image_id;

    private  String adress;
    private  String DOB;
    private String gender;


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public RiderPosts getRequests_sent_for_post() {
        return requests_sent_for_post;
    }

    public void setRequests_sent_for_post(RiderPosts requests_sent_for_post) {
        this.requests_sent_for_post = requests_sent_for_post;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
