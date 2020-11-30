package com.ezra.service.impl;

import com.ezra.service.IBlogService;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl extends ElasticSearchServiceImpl implements IBlogService {
}
