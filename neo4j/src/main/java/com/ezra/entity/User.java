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

@NodeEntity(label = "User")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * 业务节点id,为用户表主键id 或者 wxUserId
     */
    @Id
    @GeneratedValue(strategy = StringIdStrategy.class)
    @Convert(value = StringIdConverter.class)
    private String entityId;


    @Property(name = "corpId")
    private String corpId;

}
