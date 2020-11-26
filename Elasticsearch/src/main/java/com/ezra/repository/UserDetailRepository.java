package com.ezra.repository;

import com.ezra.document.UserDetailDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface UserDetailRepository extends ElasticsearchCrudRepository<UserDetailDocument, Long> {
}
