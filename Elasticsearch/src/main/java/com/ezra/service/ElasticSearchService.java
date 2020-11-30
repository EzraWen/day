package com.ezra.service;

import java.io.IOException;

public interface ElasticSearchService {


    Boolean insertChild(String index,String id,String routing,Object source) throws IOException;
}
