package com.yc.springinaction.controller;

import com.yc.springinaction.model.User;
import com.yc.springinaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admins")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView listUsers(Model model){

        return new ModelAndView("/admin/index", "menulist", model);
    }


    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String listUsers(@RequestParam(value = "async", required = false) boolean async,
                            @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                            @RequestParam(value = "name", required = false, defaultValue = "") String name,
                            Model model){

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
//        List<User> userList = userService.listUser();
        System.out.println("pageIndex:"+pageIndex);
        System.out.println("pageSize:"+pageSize);
        Page<User> page = userService.getUsersByNameLike(name, pageable);
        List<User> userList = page.getContent(); //the content od current page

        System.out.println("-----------------");
        System.out.println(userList.size());

        // model.addAttribute("page", page);
        model.addAttribute("users", userList);

        return async==true?"/users/list :: #mainContainerReplace" : "/users/list";
    }
}
