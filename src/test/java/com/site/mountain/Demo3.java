package com.site.mountain;

import com.site.mountain.server.ESClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class Demo3 {
    String index = "book";
    String type = "xuanhuan";
    RestHighLevelClient esClient = ESClient.getRestHighLevelClient();

    @Test
    public void term() throws IOException {
        // 1.创建Request对象
        SearchRequest request = new SearchRequest(index);
        request.types(type);
        // 2.指定查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(0);
        builder.size(5);
        builder.query(QueryBuilders.termQuery("author", "曹雪芹"));
        request.source(builder);
        // 3.执行查询
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        // 4.获取_source中的数据，并显示
        for (SearchHit hit : response.getHits().getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(map);
        }
    }

    @Test
    public void terms() throws IOException {
        //1.创建Request对象
        SearchRequest request = new SearchRequest(index);
        request.types(type);
        //2.指定查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(0);
        builder.size(10);
        builder.query(QueryBuilders.termsQuery("author", "曹雪芹", "施耐庵"));
        request.source(builder);
        //3.执行查询
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        //4.获取_soruce中的数据，并显示
        for (SearchHit hit : response.getHits().getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(map);
        }
    }

    @Test
    public void range() throws IOException {
        //1.创建Request对象
        SearchRequest request = new SearchRequest(index);
        request.types(type);
        //2.指定查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.rangeQuery("count").gte(100000).lte(590000));
        request.source(builder);
        //3.执行查询
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        //4.获取_soruce中的数据，并显示
        for (SearchHit hit : response.getHits().getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(map);
        }
    }

    @Test
    public void exists() throws IOException {
        SearchRequest request = new SearchRequest(index);
        request.types(type);

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.existsQuery("name"));
        request.source(builder);

        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(map);
        }
    }

    @Test
    public void bool() throws IOException {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(0).size(10);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("desr", "四大名著");
        boolQueryBuilder.must(termQueryBuilder).must(QueryBuilders.termQuery("desr", "四大名著"));
        boolQueryBuilder.mustNot(QueryBuilders.termQuery("count", 100000));
        boolQueryBuilder.should(QueryBuilders.termQuery("desr", "四大名著"))
                .should(QueryBuilders.termQuery("desr", "中国"));
        builder.query(boolQueryBuilder);
        request.source(builder);

        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(map);
        }
    }

    @Test
    public void matchAll() throws IOException {
        // 1.创建Request对象
        SearchRequest request = new SearchRequest(index);
        request.types(type);
        // 2.指定查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        request.source(builder);
        // 3.执行查询
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        // 4.获取_soruce中的数据，并显示
        for (SearchHit hit : response.getHits().getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(map);
        }
    }

    @Test
    public void match() throws IOException {
        // 1.创建Request对象
        SearchRequest request = new SearchRequest(index);
        request.types(type);
        // 2.指定查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("name", "三国"));
        request.source(builder);
        // 3.执行查询
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        // 4.获取_soruce中的数据，并显示
        for (SearchHit hit : response.getHits().getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(map);
        }
    }


    @Test
    public void multiMatch() throws IOException {
        // 1.创建Request对象
        SearchRequest request = new SearchRequest(index);
        request.types(type);
        // 2.指定查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(0).size(10);
        builder.query(QueryBuilders.multiMatchQuery("中国", "name", "desr"));
        request.source(builder);
        // 3.执行查询
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        // 4.获取_soruce中的数据，并显示
        for (SearchHit hit : response.getHits().getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(map);
        }
    }

    @Test
    public void searchBool() throws IOException {
        // 1.request
        SearchRequest request = new SearchRequest(index);
        request.types(type);
        // 2.指定查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("desr", "四大名著"))
                .should(QueryBuilders.matchQuery("name", "三国演义"))
                .should(QueryBuilders.matchQuery("desr", ""))
                .mustNot(QueryBuilders.matchQuery("name", "西游记"))

        );
        request.source(builder);
        // 3.执行查询
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        // 4.返回查询结果
        for (SearchHit hit : response.getHits().getHits()) {
            Map<String, Object> map = hit.getSourceAsMap();
            System.out.println(map);
        }
    }
}
