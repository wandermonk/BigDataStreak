package com.cisco.kafka.clients;

public class UserMeta {

    private String userId;
    private String userName;
    private String userLocation;

    public UserMeta(String userId, String userName, String userLocation) {
        this.userId = userId;
        this.userName = userName;
        this.userLocation = userLocation;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserLocation() {
        return userLocation;
    }

}
