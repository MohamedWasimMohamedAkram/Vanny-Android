package com.example.vanny;
public class Report {

    String Priority, Title, Message, filePath, timeStamp;
    int imageId;

    public Report(String Priority, String Title, String Message, String filePath, String timeStamp) {
        this.Priority = Priority;
        this.Title = Title;
        this.Message = Message;
        this.filePath = filePath;
        this.timeStamp = timeStamp;
    }
}