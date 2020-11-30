package com.ezra.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ezra.service.ElasticSearchService;
import com.ezra.util.ToolUtil;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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

    @Override
    public <T> List<T> getByParentId(Long parentId, String childIndex, Class<T> childClass) throws IOException {
        Document annotation = childClass.getAnnotation(Document.class);
        if (annotation == null || ToolUtil.isEmpty(annotation.indexName())) {
            return Collections.emptyList();
        }
        String indexName = annotation.indexName();
        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.routing(String.valueOf(parentId));
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        Iterator<SearchHit> iterator = hits.iterator();
        List<T> data = new ArrayList<>();
        while (iterator.hasNext()) {
            SearchHit next = iterator.next();
            T t = BeanUtil.toBean(next.getSourceAsString(), childClass);
            data.add(t);
        }
        return data;
    }
}
