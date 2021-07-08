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

@NodeEntity(label = "Customer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = StringIdStrategy.class)
    @Convert(value = StringIdConverter.class)
    private String entityId;


    @Property(name = "corpId")
    private String corpId;
}
