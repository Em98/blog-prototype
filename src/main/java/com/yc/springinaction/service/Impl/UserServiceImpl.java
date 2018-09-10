package com.yc.springinaction.service.Impl;

import com.yc.springinaction.Repository.UserRepository;
import com.yc.springinaction.model.User;
import com.yc.springinaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Primary
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Transactional
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void removeUser(User user) {
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public void removeUsersInBatch(List<User> users) {
        userRepository.deleteInBatch(users);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getUsersByNameLike(String name, Pageable pageable) {
        name = "%"+name+"%";
        return userRepository.findByNameLike(name, pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }

    @Override
    public User getUserByUsername(String usernmae) {
        return userRepository.findByUsername(usernmae);
    }
}
