package com.ezra.repository;

import com.ezra.entity.Customer;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends Neo4jRepository<Customer, String> {

}
