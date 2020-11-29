package com.ezra.repository;

import com.ezra.document.BlogDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BlogRepository extends ElasticsearchRepository<BlogDocument, Long> {
}
