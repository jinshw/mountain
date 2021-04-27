package com.site.mountain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.mountain.entity.Person;
import com.site.mountain.server.ESClient;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;

public class Demo2 {
    ObjectMapper mapper = new ObjectMapper();
    RestHighLevelClient esClient = ESClient.getRestHighLevelClient();
    String index = "person";
    String type = "man";

    @Test
    public void createDoc() throws IOException, ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //1.准备json数据
        // 在添加时间格式字段时，解决方案：先把时间格式转换成字符串
        Person person = new Person(3, "李四22", 34, simpleDateFormat.format(new Date()));
        String json = mapper.writeValueAsString(person);
        //2.通过request对象
        IndexRequest indexRequest = new IndexRequest(index, type, person.getId().toString());
        indexRequest.source(json, XContentType.JSON);
        //3.通过client对象执行添加
        IndexResponse indexResponse = esClient.index(indexRequest, RequestOptions.DEFAULT);
        //4.输出返回结果
        System.out.println(indexResponse.getResult().toString());
    }

    @Test
    public void updateDoc() throws IOException {
        // 1.准备数据：创建一个数据，指定一个需要修改的内容
        Map<String, Object> map = new HashMap<>();
        map.put("name", "王五");
        String docId = "2";
        // 2.创建request对象，封装数据
        UpdateRequest updateRequest = new UpdateRequest(index, type, docId);
        updateRequest.doc(map);
        // 3.通过client更新数据
        UpdateResponse response = esClient.update(updateRequest, RequestOptions.DEFAULT);
        // 4.返回结果
        System.out.println(response.getResult().toString());
    }

    @Test
    public void deleteDoc() throws IOException {
        // 1.封装Request对象
        DeleteRequest deleteRequest = new DeleteRequest(index, type, "3");
        // 2.client执行
        DeleteResponse response = esClient.delete(deleteRequest, RequestOptions.DEFAULT);
        // 3.输出结果
        System.out.println(response.getResult().toString());
    }

    @Test
    public void bulkCreateDoc() throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 1.准备多个json数据
        Person p1 = new Person(4, "赵钱孙4", 104, simpleDateFormat.format(new Date()));
        Person p2 = new Person(5, "赵钱孙5", 105, simpleDateFormat.format(new Date()));
        Person p3 = new Person(6, "赵钱孙6", 106, simpleDateFormat.format(new Date()));
        Person p4 = new Person(7, "赵钱孙7", 107, simpleDateFormat.format(new Date()));
        String json1 = mapper.writeValueAsString(p1);
        String json2 = mapper.writeValueAsString(p2);
        String json3 = mapper.writeValueAsString(p3);
        String json4 = mapper.writeValueAsString(p4);

        // 2.创建Request，将准备好的数据封装进去
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest(index, type, p1.getId().toString()).source(json1, XContentType.JSON));
        bulkRequest.add(new IndexRequest(index, type, p2.getId().toString()).source(json2, XContentType.JSON));
        bulkRequest.add(new IndexRequest(index, type, p3.getId().toString()).source(json3, XContentType.JSON));
        bulkRequest.add(new IndexRequest(index, type, p4.getId().toString()).source(json4, XContentType.JSON));

        // 3.用client执行
        BulkResponse response = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        // 4.输出结果
        System.out.println(response.toString());
    }

    @Test
    public void bulkDeleteDoc() throws IOException {
        // 1.封装Request
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest(index, type, "4"));
        request.add(new DeleteRequest(index, type, "5"));
        request.add(new DeleteRequest(index, type, "6"));
        request.add(new DeleteRequest(index, type, "7"));

        // 2.执行client
        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);

        // 3.输出结果
        System.out.println(response.toString());
    }

}
