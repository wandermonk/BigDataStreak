package com.cisco.kafka.clients;

public class EventMeta {

    private String eventId;
    private String eventName;
    private String eventLocation;

    public EventMeta(String eventId, String eventName, String eventLocation) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventLocation = eventLocation;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

}
