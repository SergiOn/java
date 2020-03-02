package com.github.simplesteph.kafka.tutorial1;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerDemoKeys {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final Logger logger = LoggerFactory.getLogger(ProducerDemoKeys.class);

        String bootstrapServers = "localhost:9092";

        // create producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);


        for (int i = 0; i < 10; i++) {

            final String topic = "first_topic";
            final String value = "Hello world " + Integer.toString(i);
            final String key = "id_" + Integer.toString(i);

            // create the producer
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);

            logger.info("Key: {}", key); // log the key
            // id_0 is going to partition 1
            // id_1 partition 0
            // id_2 partition 2
            // id_3 partition 0
            // id_4 partition 2
            // id_5 partition 2
            // id_6 partition 0
            // id_7 partition 2
            // id_8 partition 1
            // id_9 partition 2

            // send data - (asynchronous) synchronous
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
            }).get(); // block the .send() to make it synchronous - don't do this in production!
        }

        // flush data
        producer.flush();
        // flush and close producer
        producer.close();

    }

}
