package com.douyu.search.elasticsearchsimple;

import com.douyu.search.elasticsearchsimple.client.ESClient;
import com.douyu.search.elasticsearchsimple.config.ESConfig;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHits;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xialian
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.douyu.search")
public class ElasticsearchSimpleApplication{

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchSimpleApplication.class, args);
        getClient();
    }

    public static void getClient(){
        TransportClient client = ESClient.client();
        System.out.println(client);
    }

}
