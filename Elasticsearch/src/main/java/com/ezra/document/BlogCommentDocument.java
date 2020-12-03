package com.ezra.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.HashMap;
import java.util.Map;

@Data
@Document(indexName = "blog_data", createIndex = false, shards = 5, replicas = 5)
/**
 * shards 分片
 * replicas 备份
 */
public class BlogCommentDocument {

//    shard = hash(routing) % number_of_primary_shards

    private Long id;
    private String remark;


    private Map<String, Object> relation;

    public void setParent(Long parentId) {
        relation = new HashMap<>();
        relation.put("name", "comment");
        relation.put("parent", parentId);
    }
}
