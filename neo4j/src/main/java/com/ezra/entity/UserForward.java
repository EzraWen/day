package com.ezra.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

/**
 * 用户给客户的关系
 */
@RelationshipEntity(type = "UserForward")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserForward {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private User user;

    @EndNode
    private Customer customer;

    @Property(name = "start")
    private String start;

    @Property(name = "end")
    private String end;

    @Property(name = "materialId")
    private String materialId;
}
