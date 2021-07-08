package com.ezra.entity;

import com.ezra.config.StringIdConverter;
import com.ezra.config.StringIdStrategy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.Convert;

/**
 * 素材从属关系
 */
@RelationshipEntity(type = "Belong")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Belong {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Material material;

    @EndNode
    private User user;
}
