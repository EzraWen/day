package com.ezra.service.impl;

import com.ezra.service.MomentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MomentContext {

    @Autowired
    private Map<String, MomentStrategy> momentStrategyMap;


    public void show() {
        System.out.println(momentStrategyMap);
        System.out.println("--");
    }
}
