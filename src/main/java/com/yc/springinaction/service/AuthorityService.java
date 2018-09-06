package com.yc.springinaction.service;

import com.yc.springinaction.model.Authority;

public interface AuthorityService {

    /**
     * get the Authority object by id
     * @param id
     * @return
     */
    Authority getAuthorityById(Long id);
}
