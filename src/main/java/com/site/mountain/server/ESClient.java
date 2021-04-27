package com.site.mountain.server;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class ESClient {
    public static String url = "192.168.75.139";
    public static Integer port = 9200;

    public static RestHighLevelClient getRestHighLevelClient() {
        // 创建HttpHost对象
        HttpHost httpHost = new HttpHost(url, port);
        // 创建RestClientBuilder
        RestClientBuilder restClientBuilder = RestClient.builder(httpHost);
        // 创建RestHighLevelClient
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        // 返回
        return restHighLevelClient;
    }
}
