package com.yc.springinaction.service;

import com.yc.springinaction.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    /**
     * save a new user
     * @param user
     * @return
     */
    User saveUser(User user);

    /**
     * update a certain user
     * @param user
     * @return
     */
    User updateUser(User user);

    /**
     * remove a user
     * @param user
     */
    void removeUser(User user);

    /**
     * remove a list of users in one time
     * @param users
     */
    void removeUsersInBatch(List<User> users);

    /**
     * get user by id
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * get the list of all users
     * @return
     */
    List<User> listUser();

    /**
     * get user list by namelike query
     * @param name
     * @param pageable
     * @return
     */
    Page<User> getUsersByNameLike(String name, Pageable pageable);

}
