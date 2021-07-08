package com.ezra.config;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.neo4j.ogm.id.IdStrategy;

public class StringIdStrategy implements IdStrategy {

    @Override
    public Object generateId(Object o) {
        return IdWorker.getIdStr();
    }
}
