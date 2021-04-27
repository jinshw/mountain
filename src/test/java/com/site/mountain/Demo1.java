package com.site.mountain;

import com.site.mountain.server.ESClient;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.junit.Test;

import java.io.IOException;

public class Demo1 {
    RestHighLevelClient esClient = ESClient.getRestHighLevelClient();
    String index = "person";
    String type = "man";

    /**
     * 创建索引
     * @throws IOException
     */
    @Test
    public void createIndex() throws IOException {
        // 1. 准备关于索引的settings
        Settings.Builder settings = Settings.builder()
                .put("number_of_shards", 5)
                .put("number_of_replicas", 1);

        // 2. 准备关于索引的结构mapping
        XContentBuilder mappings = JsonXContent.contentBuilder()
                .startObject()
                .startObject("properties")
                .startObject("name")
                .field("type", "text")
                .endObject()
                .startObject("age")
                .field("type", "integer")
                .endObject()
                .startObject("birthday")
                .field("type", "date")
                .field("format", "yyyy-MM-dd")
                .endObject()
                .endObject()
                .endObject();


        // 3. 将settings和mappings封装到一个Request对象中
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index)
                .settings(settings)
                .mapping(type, mappings);
        // 4. 通过client连接ES并且执行创建索引
        CreateIndexResponse response = esClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        // 5. 输出
        System.out.println("resp" + response.toString());
    }

    @Test
    public void exists() throws IOException {
        //1.准备request对象
        GetIndexRequest getIndexRequest = new GetIndexRequest();
        getIndexRequest.indices(index);
        //2.通过client去操作
        boolean exists = esClient.indices().exists(getIndexRequest,RequestOptions.DEFAULT);
        System.out.println(index +"存在："+exists);
    }

    @Test
    public void deleteIndex() throws IOException {
        //1.创建request对象
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest();
        deleteIndexRequest.indices(index);
        //2.通过client去操作
        AcknowledgedResponse response = esClient.indices().delete(deleteIndexRequest,RequestOptions.DEFAULT);
        //3.获取返回结果
        System.out.println(response.isAcknowledged());
    }
}
