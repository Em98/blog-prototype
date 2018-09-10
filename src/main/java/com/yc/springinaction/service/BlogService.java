package com.yc.springinaction.service;

import com.yc.springinaction.model.Blog;
import com.yc.springinaction.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {

    /**
     * 保存博客
     * @param blog
     * @return
     */
    Blog save(Blog blog);

    /**
     * 更新博客
     * @param blog
     * @return
     */
    Blog updateBlog(Blog blog);

    /**
     * 删除博客
     * @param id
     */
    void removeBlog(Long id);

    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    Blog getBlogById(Long id);

    List<Blog> listAllBlog();

    /**
     * 根据时间倒叙分页
     * @param pageable
     * @return
     */
    Page<Blog> listAllBlogByDesc(Pageable pageable);

    Page<Blog> listUserBlogByTimeDesc(User user, Pageable pageable);
}
