package com.ezra.mapping;


import com.ezra.constant.SystemConstant;
import com.ezra.document.BlogCommentDocument;
import com.ezra.document.BlogDocument;
import com.ezra.repository.BlogCommentRepository;
import com.ezra.repository.BlogRepository;
import com.ezra.response.Result;
import com.ezra.service.IBlogService;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(SystemConstant.API_URL + "/mapping/blog")
public class BlogMapping {

    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private ElasticsearchRestTemplate restTemplate;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private BlogCommentRepository blogCommentRepository;
    @Autowired
    private IBlogService blogService;

    /**
     * 用于创建新的mapping
     *
     * @return
     */
    @PostMapping("/init")
    public Result createMapping() throws IOException {

        //创建索引blog
        CreateIndexRequest blogData = new CreateIndexRequest("blog_data");
        blogData.settings(Settings.builder().put("index.number_of_shards", 2).put("index.number_of_replicas", 0));
        client.indices().create(blogData, RequestOptions.DEFAULT);

        XContentBuilder builder = null;
        builder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties")
                .startObject("blog")
                .startObject("properties")
                .startObject("id")
                .field("type", "long")
                .endObject()
                .startObject("title")
                .field("type", "text")
                .field("index", false)
                .endObject()
                .startObject("addtime")
                .field("type", "date")
                .field("format", "yyyy-MM-dd HH:mm:ss")
                .endObject()
                .endObject()
                .endObject()


                .startObject("comment")
                .startObject("properties")
                .startObject("id")
                .field("type", "long")
                .endObject()
                .startObject("ramark")
                .field("type", "text")
                .field("index", false)
                .endObject()
                .endObject()
                .endObject()


                .startObject("relation")
                .field("type", "join")
                .startObject("relations")
                .field("blog", "comment")
                .endObject()
                .endObject()
                .endObject()
                .endObject();


        PutMappingRequest mapping = Requests
                .putMappingRequest("blog_data")
                .type("blog_data")
                .source(builder);
        client.indices().putMapping(mapping, RequestOptions.DEFAULT);
        return Result.SUCCESS;
    }


    @PostMapping("/add")
    public Result add() {
        BlogDocument blogDocument = new BlogDocument();
        blogDocument.setId(1l);
        blogDocument.setTitle("第一个文章");
        blogDocument.setAddTime(LocalDateTime.now());
        blogRepository.save(blogDocument);
        return Result.SUCCESS;
    }

    @PostMapping("/get")
    public Result get(@RequestParam("id") Long id) {
        Optional<BlogDocument> byId = blogRepository.findById(id);
        return byId.isPresent() ? Result.data(byId.get()) : Result.data(null);
    }

    @PostMapping("/getComment")
    public Result getComment(@RequestParam("id") Long id) {
        Optional<BlogCommentDocument> byId = blogCommentRepository.findById(id);
        return byId.isPresent() ? Result.data(byId.get()) : Result.data(null);
    }


    @PostMapping("/addComment")
    public Result addComment(@RequestParam("id") Long id) throws IOException {
        BlogCommentDocument blogCommentDocument = new BlogCommentDocument();
        blogCommentDocument.setId(id);
        blogCommentDocument.setRemark("评论111" + id);
        blogCommentDocument.setParent(1l);
        Boolean result = blogService.insertChild("blog_data", String.valueOf(blogCommentDocument.getId()),
                "1", blogCommentDocument);
        return result ? Result.SUCCESS : Result.BUSINESS_FAIL;
    }


    @PostMapping("/getCommentByParent")
    public Result getCommentByParent(@RequestParam("id") Long id) throws IOException {
        List<BlogCommentDocument> comment = blogService.getByParentId(id, "comment", BlogCommentDocument.class);
        return Result.data(comment);
    }


//    {
//        "properties": {
//        "parent": {
//            "properties": {
//                "id": {
//                    "type": "integer"
//                },
//                "parentName": {
//                    "type": "text"
//                }
//            }
//        },
//        "child": {
//            "properties": {
//                "id": {
//                    "type": "integer"
//                },
//                "childName": {
//                    "type": "text"
//                },
//                "parentId": {
//                    "type": "integer"
//                }
//            }
//        },
//        "relation": {
//            "type": "join",
//             "relations": {
//                "parent": [
//                "child"
//        ]
//            }
//        }
//    }
//    }

}
