package com.ezra.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ezra.service.ElasticSearchService;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public Boolean insertChild(String index, String id, String routing, Object source) throws IOException {
        UpdateRequest request = new UpdateRequest(index, id);
        IndexRequest indexRequest = new IndexRequest(index);
        Map<String, Object> source1 = BeanUtil.beanToMap(source);
        indexRequest.source(source1);
        request.doc(indexRequest);
        request.routing(routing);
        request.docAsUpsert(true);
        UpdateResponse updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        Integer success = updateResponse.getShardInfo().getSuccessful();
        return success > 0;
    }
}
