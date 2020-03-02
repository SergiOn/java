package com.github.simplesteph.kafka.tutorial1;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithCallback {

//    Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallback.class);
//    Logger logger = LoggerFactory.getLogger(this.getClass());
//    Logger logger = LoggerFactory.getLogger(this.getClass().getName());
//    Logger logger = LoggerFactory.getLogger(getClass());


    public static void main(String[] args) {
//        System.out.println("hello world!");

        final Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallback.class);

        String bootstrapServers = "localhost:9092";

        // create producer properties
        Properties properties = new Properties();
//        properties.setProperty("bootstrap.servers", bootstrapServers);
//        properties.setProperty("key.serializer", StringSerializer.class.getName());
//        properties.setProperty("value.serializer", StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);


        for (int i = 0; i < 10; i++) {

            // create the producer
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("first_topic", "Hello world " + Integer.toString(i));

            // send data - asynchronous
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    // executes every time a record is successfully sent or an exception is thrown
                    if (e == null) {
                        // the record was successfully sent
                        logger.info("Received new metadata. \n" +
                                    "Topic: {} \n" +
                                    "Partition: {} \n" +
                                    "Offset: {} \n" +
                                    "Timestamp: {}",
                                recordMetadata.topic(),
                                recordMetadata.partition(),
                                recordMetadata.offset(),
                                recordMetadata.timestamp());
                    } else {
                        logger.info("Error while producing", e);
                    }
                }
            });
        }

        // flush data
        producer.flush();
        // flush and close producer
        producer.close();

    }

}
