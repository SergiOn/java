package com.github.simplesteph.kafka;

import org.apache.http.HttpHost;
import org.apache.kafka.common.protocol.types.Field;
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

public class ElasticSearchConsumer {

    public static RestHighLevelClient createClient() {
        final String protocol = "http";
        final String hostname = "localhost";
        final int port = 9200;

        RestClientBuilder builder = RestClient.builder(new HttpHost(hostname, port, protocol));
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }

    public static void main(String[] args) throws IOException {

        final Logger logger = LoggerFactory.getLogger(ElasticSearchConsumer.class);

        final RestHighLevelClient client = createClient();

        final String jsonString = "{ \"foo\": \"bar\" }";

        final IndexRequest indexRequest = new IndexRequest("twitter").source(jsonString, XContentType.JSON);

        final IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        final String id = indexResponse.getId();
        logger.info(id);

        // close the client gracefully
        client.close();

    }


}
