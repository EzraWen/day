package com.ezra.repository;

import com.ezra.entity.CustomerForward;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerForwardRepository extends Neo4jRepository<CustomerForward, Long> {

}

