package com.xin.aggregatesearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xin
 * @create 2022/02/20 22:21
 * @description
 **/
@Configuration
public class ElasticSearchConfig {

    @Bean
    public RestHighLevelClient getSearchConfig(){
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("47.116.128.246",9200)
                )
        );
    }
}
