package com.yc.springinaction.service.Impl;

import com.yc.springinaction.Repository.AuthorityRepository;
import com.yc.springinaction.model.Authority;
import com.yc.springinaction.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService{

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority getAuthorityById(Long id) {
        return authorityRepository.getOne(id);
    }
}
