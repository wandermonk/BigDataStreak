package com.cisco.kafka.clients;

import java.util.Date;

public class UserRating {
    private String userID;
    private String userName;
    private String userLocation;
    private String eventID;
    private String eventName;
    private Date eventDate;
    private String eventLocation;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    @Override
    public String toString() {
        return "UserRating{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", userLocation='" + userLocation + '\'' +
                ", eventID='" + eventID + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDate=" + eventDate +
                ", eventLocation='" + eventLocation + '\'' +
                '}';
    }
}
