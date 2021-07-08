package com.ezra.entity;

import org.apache.commons.lang3.StringUtils;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Labels;

import java.util.HashSet;
import java.util.Set;

public class BaseNode {
    @Id
    @GeneratedValue
    protected Long id;
    /**
     * used for dynamically adding new Label,the default label(Class name) is not included
     */
    @Labels
    private Set<String> labels = new HashSet<>();

    public BaseNode() {
    }

    public Long getId() {
        return id;

    }

    public void addLabel(String newLabel) {
        if (StringUtils.isNotEmpty(newLabel)) {
            labels.add(newLabel.toUpperCase());

        }
    }
}