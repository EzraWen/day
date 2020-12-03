package com.ezra.service;

import java.io.IOException;
import java.util.List;

public interface ElasticSearchService {

    /**
     *  增加子类型文档，父类型文档使用repository新增即可
     * @param index 索引，可优化，从source中获取文档注解中的Index
     * @param id  文档id，可优化，从source中获取Id
     * @param routing  父文档id，可优化，从source中获取父id
     * @param source 当前文档
     * @return
     * @throws IOException
     */
    Boolean insertChild(String index,String id,String routing,Object source) throws IOException;

    <T> List<T> getByParentId(Long parentId,String childIndex,Class<T> childClass) throws IOException;

}
