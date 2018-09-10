package com.yc.springinaction.Repository;

import com.yc.springinaction.model.Blog;
import com.yc.springinaction.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    /**
     * 根据用户进行分页查询
     * @param user
     * @param pageable
     * @return
     */
    Page<Blog> findByUserOrderByTimestampDesc(User user, Pageable pageable);

    Page<Blog> findAll(Pageable pageable);
}
