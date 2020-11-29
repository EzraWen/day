package com.ezra.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Document(indexName = "blog_data", createIndex = false, shards = 1, replicas = 5)
public class BlogCommentDocument {


    private Long id;
    private String remark;

    private BaseRelation relation;

    public void setParent(Long parentId){
        relation =new BaseRelation();
        relation.setParent(parentId);
        relation.setName("comment");
    }
}
