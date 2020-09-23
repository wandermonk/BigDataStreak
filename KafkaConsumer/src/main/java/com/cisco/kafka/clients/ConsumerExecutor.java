package com.cisco.kafka.clients;

public class ConsumerExecutor {

  public static void main(String[] args) {
    UserEventsConsumer consumer = new UserEventsConsumer("events");
    consumer.consume();
  }
}
