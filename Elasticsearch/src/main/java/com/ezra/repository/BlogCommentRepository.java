package com.ezra.repository;

import com.ezra.document.BlogCommentDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BlogCommentRepository extends ElasticsearchRepository<BlogCommentDocument, Long> {
}
