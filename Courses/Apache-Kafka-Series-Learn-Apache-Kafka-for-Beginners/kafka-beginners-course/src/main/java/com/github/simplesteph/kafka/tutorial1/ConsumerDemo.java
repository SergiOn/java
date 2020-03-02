package com.github.simplesteph.kafka.tutorial1;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConsumerDemo {

    public static void main(String[] args) {
//        System.out.println("hello word!");

        Logger logger = LoggerFactory.getLogger(ConsumerDemo.class.getName());

        final String bootstrapServers = "localhost:9092";
        final String groupId = "my-fourth-application";
        final String topic = "first_topic";

        // create consumer config
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // "latest" / "earliest" / "none"

        // create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        // subscribe consumer to our topic(s)
//        consumer.subscribe(Collections.singleton(topic));
//        consumer.subscribe(Arrays.asList(topic, "second_topic"));
        consumer.subscribe(Arrays.asList(topic));


        // poll for new data
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100)); // new in Kafka 2.0.0

            for (ConsumerRecord<String, String> record : records) {
                logger.info("Key: {}, Value: {}", record.key(), record.value());
                logger.info("Partition: {}, Offset: {}", record.partition(), record.offset());
            }
        }


    }

}
