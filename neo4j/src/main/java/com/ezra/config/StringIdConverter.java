package com.ezra.config;

import org.neo4j.ogm.typeconversion.AttributeConverter;

public class StringIdConverter implements AttributeConverter<String,String> {

    @Override
    public String toGraphProperty(String s) {
        return s;
    }

    @Override
    public String toEntityAttribute(String s) {
        return s;
    }
}
