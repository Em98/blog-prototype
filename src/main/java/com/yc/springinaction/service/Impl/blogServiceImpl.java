package com.yc.springinaction.service.Impl;

import com.yc.springinaction.Repository.BlogRepository;
import com.yc.springinaction.model.Blog;
import com.yc.springinaction.model.User;
import com.yc.springinaction.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class blogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void removeBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public List<Blog> listAllBlog() {
        return blogRepository.findAll();
    }

    @Override
    public Page<Blog> listAllBlogByDesc(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> listUserBlogByTimeDesc(User user, Pageable pageable) {
        return blogRepository.findByUserOrderByTimestampDesc(user, pageable);
    }
}
