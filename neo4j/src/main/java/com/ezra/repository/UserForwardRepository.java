package com.ezra.repository;

import com.ezra.entity.UserForward;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserForwardRepository extends Neo4jRepository<UserForward, Long> {

}

