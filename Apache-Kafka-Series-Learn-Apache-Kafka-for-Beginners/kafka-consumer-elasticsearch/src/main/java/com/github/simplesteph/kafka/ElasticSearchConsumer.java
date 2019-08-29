package com.github.simplesteph.kafka;

import com.google.gson.JsonParser;
import org.apache.http.HttpHost;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ElasticSearchConsumer {

    private final static JsonParser jsonParser = new JsonParser();

    private static RestHighLevelClient createClient() {
        final String protocol = "http";
        final String hostname = "localhost";
        final int port = 9200;

        final RestClientBuilder builder = RestClient.builder(new HttpHost(hostname, port, protocol));
        final RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }

    private static KafkaConsumer<String, String> createConsumer(String topic) {

        final String bootstrapServers = "localhost:9092";
        final String groupId = "kafka-demo-elasticsearch";

        // create consumer config
        final Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // "latest" / "earliest" / "none"
        // implementation, v2
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false"); // disable autocommit of offsets
//        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10");
        // implementation, v3
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "100");

        // create consumer
        final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(topic));

        return consumer;
    }

    public static void main(String[] args) throws IOException {

        final Logger logger = LoggerFactory.getLogger(ElasticSearchConsumer.class);
        final String topic = "twitter_tweets";
        final String index = "twitter";

        final RestHighLevelClient client = createClient();
        final KafkaConsumer<String, String> consumer = createConsumer(topic);

        // poll for new data
        while (true) {
            final ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100)); // new in Kafka 2.0.0

            // implementation, v2
//            logger.info("Received " + records.count() + " records");

            // implementation, v3
            final int recordCount = records.count();
            logger.info("Received {} records", recordCount);

            // implementation, v3
            final BulkRequest bulkRequest = new BulkRequest();

            for (ConsumerRecord<String, String> record : records) {

                // 2 strategies
                // kafka generic id
//                final String id = record.topic() + "_" + record.partition() + "_" + record.offset();

                // twitter feed specific id
//                final String id = extractIdFromTweets(record.value());

                // where we insert data to ElasticSearch
//                final IndexRequest indexRequest = new IndexRequest(index).source(record.value(), XContentType.JSON)
//                final IndexRequest indexRequest = new IndexRequest(index).source(record.value(), XContentType.JSON).id(id);

                /*
                final IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
//                logger.info(record.topic() + "_" + record.partition() + "_" + record.offset());
                logger.info(id);
//                logger.info(indexResponse.getId());
//                logger.info(record.value());

                try {
                    // introduce a small delay
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } */

                // implementation, v3
                try {
                    // twitter feed specific id
                    final String id = extractIdFromTweets(record.value());

                    // where we insert data to ElasticSearch
                    final IndexRequest indexRequest = new IndexRequest(index).source(record.value(), XContentType.JSON).id(id);
                    bulkRequest.add(indexRequest); // we add to our bulk request (takes no time)
                } catch (NullPointerException e) {
                    logger.warn("skipping bad data: {}", record.value());
                }
            }

            // implementation, v2
//            logger.info("Committing offsets...");
//            consumer.commitSync();
//            logger.info("Offsets have been committed");

            // implementation, v3
            if (recordCount > 0) {
                final BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);

                logger.info("Committing offsets...");
                consumer.commitSync();
                logger.info("Offsets have been committed");

                try {
                    // introduce a small delay
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // close the client gracefully
        // client.close();

    }

    private static String extractIdFromTweets(String tweetJson) {
        // gson library
        return jsonParser.parse(tweetJson)
                .getAsJsonObject()
                .get("id_str")
                .getAsString();
    }


}
