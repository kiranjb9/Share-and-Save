package com.example.kiran.carpool.Util;

public class StaticClass {
    public   static String userID;
    public   static String postID;

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        StaticClass.userID = userID;
    }

    public static String getPostID() {
        return postID;
    }

    public static void setPostID(String postID) {
        StaticClass.postID = postID;
    }
}
