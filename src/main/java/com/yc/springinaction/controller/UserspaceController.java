package com.yc.springinaction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/u")
public class UserspaceController {
    @GetMapping("/{username")
    public String userSpace(@PathVariable("username") String username){
        System.out.println("username: "+username);
        return "/userspace/u";
    }

    @GetMapping("/{username}/blogs")
    public String listBlogsByOrder(@PathVariable("username") String username,
                                   @RequestParam(value = "order", required = false, defaultValue = "new") String order,
                                   @RequestParam(value = "keyword", required = false)String keyword,
                                   @RequestParam(value = "category", required =false) Long category){
        if (category != null){
            System.out.println("category: "+ category);
            System.out.println("selflink: /u/"+username+"/blogs"+"?catogery="+category);
            return "/userspace/u";
        }
        else if (keyword != null){
            System.out.println("keyword: "+keyword);
            System.out.println("selflink: /u/"+username+"/blogs?keyword="+keyword);
            return "/userspace/u";
        }

        System.out.println("order :"+order);
        System.out.println("selflink :/u/"+username+"/blogs?order="+order);
        return "/userspace/u";
    }

    @GetMapping("/{username}/blogs/{id}")
    public String getBlogById(@PathVariable("id") Long id){
        System.out.println("blogId :"+id);
        return "/userspace/blog";
    }

    @GetMapping("/{username}/blog/{id}")
    public String editBlog(){

        return "/blogedit";
    }
}
