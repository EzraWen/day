package com.ezra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Autowired
    private DBHelper helper;
    
    @Override
    protected Object determineCurrentLookupKey() {
        return helper.getDBType();
    }
}