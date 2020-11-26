package com.ezra.service.impl;

import com.ezra.document.UserDetailDocument;
import com.ezra.repository.UserDetailRepository;
import com.ezra.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public void save(UserDetailDocument document) {
        userDetailRepository.save(document);
    }
}
