package com.github.simplesteph.kafka;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TwitterProducer {

    private final Logger logger = LoggerFactory.getLogger(TwitterProducer.class.getName());

    private final String consumerKey = "KYssmGuBkF84kk96mTnUrWW40";
    private final String consumerSecret = "ZkTlg2zlXZZ27nQcLiUwE3Xoi3XXzLUYXNXhwKP85DH9iCIXko";
    private final String token = "430207215-hHR0kRk971R9SRMkppaIM8ta4c29QVwrDEr5zbKS";
    private final String secret = "vp3BiNZg5eJSrlQIRPc2BIrwcgO97C8Uwwvkfxj2M1Y71";

    private final List<String> terms = Lists.newArrayList("bitcoin", "usa", "politics", "sport", "soccer");

    public TwitterProducer() {
    }

    public static void main(String[] args) {
        new TwitterProducer().run();
    }

    private void run() {

        logger.info("Setup");

        /* Set up your blocking queues: Be sure to size these properly based on expected TPS of your stream */
        final BlockingQueue<String> msgQueue = new LinkedBlockingQueue<>(100000);

        // create a twitter client
        final Client client = createTwitterClient(msgQueue);
        // Attempts to establish a connection.
        client.connect();


        // create a kafka producer
        final KafkaProducer<String, String> producer = createKafkaProducer();

        // add a shutdown hooks
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("stopping application...");
            logger.info("shutting down client from twitter...");
            client.stop();
            logger.info("closing producer...");
            producer.close();
            logger.info("done!");
        }));

        // loop to send tweets to kafka
        // on a different thread, or multiple different threads....
        while (!client.isDone()) {
            String msg = null;
            try {
                msg = msgQueue.poll(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                client.stop();
            }

            if (msg != null) {
                logger.info(msg);

                producer.send(new ProducerRecord<>("twitter_tweets", null, msg), (metadata, exception) -> {
                    if (exception != null) {
                        logger.error("Something bad happened", exception);
                    }
                });
            }
        }

        logger.info("End of application");
    }

    private Client createTwitterClient(BlockingQueue<String> msgQueue) {

        final Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        final StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
        hosebirdEndpoint.trackTerms(terms);

        final Authentication hosebirdAuth = new OAuth1(consumerKey, consumerSecret, token, secret);

        final ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-01")
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue));

        return builder.build();
    }

    private KafkaProducer<String, String> createKafkaProducer() {
        final String bootstrapServers = "localhost:9092";

        // create producer properties
        final Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create safe producer
        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, Boolean.toString(true));
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));
        // kafka 2.12 >= 1.1 so we can keep this as 5. Use 1 otherwise
        properties.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");

        // high throughput producer (at the expense of a bit of latency and CPU usage)
        properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "20");
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, Integer.toString(32 * 1024)); // 32 KB batch size

        // create the producer
        return new KafkaProducer<>(properties);
    }

}
