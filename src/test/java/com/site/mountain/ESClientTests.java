package com.site.mountain;

import com.site.mountain.server.ESClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

public class ESClientTests {

    @Test
    public void connectES(){
        RestHighLevelClient restClient = ESClient.getRestHighLevelClient();
        System.out.println("OK");
    }
}
