package com.yc.springinaction.Repository;

import com.yc.springinaction.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    Page<User> findByNameLike(String name, Pageable pageable);
    User findByUsername(String username);
}
