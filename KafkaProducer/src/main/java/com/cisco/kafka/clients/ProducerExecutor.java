package com.cisco.kafka.clients;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.UUID;

public class ProducerExecutor {
    public static void main(String[] args) {
        try {
            UserRatingsProducer producer = new UserRatingsProducer("show-events-producer-1");
            Producer<String, String> kafkaProducer = producer.getKafkaProducer();

            for(int i=0;i<100000;i++){
                String rating = producer.randomUserEventGenerator();
                // create a Producer Record
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>("events",
                        UUID.randomUUID().toString().replaceAll("-",""),
                        rating
                );
                // publish the record
                System.out.println("publishing the record ::: "+ rating);
                kafkaProducer.send(producerRecord, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata,
                                             Exception e) {
                        System.out.println(metadata.toString());
                        e.printStackTrace();
                    }
                });

            }

            // close the producer after work is completed
            kafkaProducer.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
