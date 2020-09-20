package com.cisco.kafka.clients;

import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class UserRatingsProducer {

    private String producerId;

    public UserRatingsProducer(String producerId){
        this.producerId = producerId;
    }

    public Producer<String, String> getKafkaProducer() throws Exception {

        // Kafka Producer Configs
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG, producerId);
        producerProperties.put(ProducerConfig.RETRIES_CONFIG, 3);
//        producerProperties.put(ProducerConfig.BATCH_SIZE_CONFIG, 1024);
//        producerProperties.put(ProducerConfig.ACKS_CONFIG, "all");
//        producerProperties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 12000);
//        producerProperties.put(ProducerConfig.LINGER_MS_CONFIG, 1);

        // create a producer instance
        Producer<String, String> kafkaProducer = new KafkaProducer<>(producerProperties);

        return kafkaProducer;

    }


    public String randomUserEventGenerator() throws Exception{

        Random generate = new Random();
        UserMeta[] users = {
                new UserMeta("john@gmail.com","john","Arizona"),
                new UserMeta("david@gmail.com", "david", "Dallas"),
                new UserMeta("sai@gmail.com", "sai", "Hyderabad"),
                new UserMeta("narayna@gmail.com", "narayna", "Texas"),
                new UserMeta("vikas@gmail.com", "vikas", "Dallas")
        };

        EventMeta[] events = {
                new EventMeta("event1","A.R.Rahman Jaiho", "US"),
                new EventMeta("event2","Google Releases", "US"),
                new EventMeta("event3","Apple Releases", "US"),
        };

        UserMeta userMeta = users[generate.nextInt(4)];
        EventMeta eventMeta = events[generate.nextInt(3)];

        UserRating rating = new UserRating();

        rating.setEventDate(new Date());
        rating.setEventID(eventMeta.getEventId());
        rating.setEventLocation(eventMeta.getEventLocation());
        rating.setEventName(eventMeta.getEventName());

        rating.setUserID(userMeta.getUserId());
        rating.setUserName(userMeta.getUserName());
        rating.setUserLocation(userMeta.getUserLocation());

        ObjectMapper om = new ObjectMapper();
        String out = om.writeValueAsString(rating);

        return out;

    }

}
