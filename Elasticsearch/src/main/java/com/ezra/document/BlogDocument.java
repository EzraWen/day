package com.ezra.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Data
@Document(indexName = "blog_data", createIndex = false, shards = 1, replicas = 5)
public class BlogDocument {


    private Long id;

    private String title;

    private LocalDateTime addTime;

    private String relation = "blog";
}
