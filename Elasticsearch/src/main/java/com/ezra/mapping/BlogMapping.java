package com.ezra.mapping;


import com.ezra.constant.SystemConstant;
import com.ezra.response.Result;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(SystemConstant.API_URL + "/mapping/blog")
public class BlogMapping {

    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    private static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        builder.addHeader("Authorization", "kyle " + "TOKEN");
        builder.setHttpAsyncResponseConsumerFactory(
                new HttpAsyncResponseConsumerFactory
                        .HeapBufferedResponseConsumerFactory(200 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }

    /**
     * 用于创建新的mapping
     *
     * @return
     */
    @PostMapping("/mapping")
    public Result createMapping() throws IOException {

        //创建索引blog
        client.indices().create(new CreateIndexRequest("blog_data"), COMMON_OPTIONS);

        XContentBuilder builder = null;
        builder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("blog_data")
                .startObject("_all")
                //关闭_all字段
                .field("enabled", false)
                .endObject()
                .startObject("_source")
                //关闭_source字段
                .field("enabled", false)
                .endObject()
                //properties：列出了文档中可能包含的每个字段的映射
                .startObject("properties")
                .startObject("id")
                .field("type", "integer")
                .field("store", true)
                .endObject()
                .startObject("title")
                .field("type", "string")
                .field("not_analyzed", "false")
                .endObject()
                .startObject("addtime")
                .field("type", "date")
                .field("format", "yyyy-MM-dd HH:mm:ss")
                .endObject()
                .startObject("relation_type")
                .field("type", "join")
                .field("eager_global_ordinals", true)
                .startObject("relations")
                .field("blog", "comment")
                .endObject()
                .endObject()
                .endObject()
                .endObject()
                .endObject();


        PutMappingRequest mapping = Requests
                .putMappingRequest("blog_data")
                .type("blog_data")
                .source(builder);
        client.indices().putMapping(mapping, COMMON_OPTIONS);
        return Result.SUCCESS;
    }
}
