package com.cisco.kafka.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class UserEventsConsumer {

  private static Properties props;
  private String topic;

  static {
    props = new Properties();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "user-ratings");
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 100);
    props.put(ConsumerConfig.CLIENT_ID_CONFIG, "user-rating-1");
    props.put(
        ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG,
        "org.apache.kafka.clients.consumer.RoundRobinAssignor");
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
  }

  public UserEventsConsumer(String topic) {
    this.topic = topic;
  }

  public void consume() {
    KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
    consumer.subscribe(Arrays.asList(topic));

    while (true) {
      try {
        ConsumerRecords<String, String> records = consumer.poll(100);
        records.forEach(
            record -> {
              ObjectMapper om = new ObjectMapper();
              try {
                UserRating rating = om.readValue(record.value(), UserRating.class);
                System.out.println(rating.toString());
              } catch (IOException e) {
                e.printStackTrace();
              }
            });
      } catch (Exception e) {
          e.printStackTrace();
      }
      consumer.commitSync();
    }
  }
}
