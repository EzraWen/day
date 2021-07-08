package com.ezra.entity;

import com.ezra.config.StringIdConverter;
import com.ezra.config.StringIdStrategy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.typeconversion.Convert;

@NodeEntity(label = "Material")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Material {

    /**
     * 业务节点id,为素材表主键
     */
    @Id
    @GeneratedValue(strategy = StringIdStrategy.class)
    @Convert(value = StringIdConverter.class)
    private String entityId;

    /**
     * 素材名称
     */
    @Property(name = "name")
    private String name;


    /**
     * 企业Id
     */
    @Property(name = "corpId")
    private String corpId;


    /**
     * 素材类型
     */
    @Property(name = "materialType")
    private Integer materialType;

}
