package com.example.vanny;
public class Report {

    String name, lastMessage, lastMsgTime, phoneNo, country;
    int imageId;

    public Report(String name, String lastMessage, String lastMsgTime, String phoneNo, String country, int imageId) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.lastMsgTime = lastMsgTime;
        this.phoneNo = phoneNo;
        this.country = country;
        this.imageId = imageId;
    }
}