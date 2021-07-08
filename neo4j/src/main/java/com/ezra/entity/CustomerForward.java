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
 * 客户转发给客户的关系
 */
@RelationshipEntity(type = "CustomerForward")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerForward {

    @Id
    @GeneratedValue
    private String id;

    @StartNode
    private Customer parent;

    @EndNode
    private Customer child;

    /**
     * 开始节点的业务id
     */
    @Property(name = "start")
    private String start;

    /**
     * 结束节点的业务id
     */
    @Property(name = "end")
    private String end;

    /**
     * 关系属于哪个素材
     */
    @Property(name = "materialId")
    private String materialId;
}
