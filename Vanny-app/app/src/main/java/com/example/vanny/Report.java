package com.example.vanny;
public class Report {

    String type, Title, Message, filePath, timeStamp;
    int imageId;

    public Report(String type, String Title, String Message, String filePath, String timeStamp, int imageId) {
        this.type = type;
        this.Title = Title;
        this.Message = Message;
        this.filePath = filePath;
        this.timeStamp = timeStamp;
        this.imageId = imageId;
    }
}