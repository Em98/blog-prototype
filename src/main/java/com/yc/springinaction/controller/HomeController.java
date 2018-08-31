package com.yc.springinaction.controller;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String toIndex(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(){
        return "/index";
    }

    @GetMapping("login")
    public String newUser(){
        return "/login";
    }


    @GetMapping("/login-error")
    public String longinError(Model model){
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg", "登陆失败，用户名或密码错误");
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "/register";
    }

    @GetMapping("/search")
    public String search(){
        return "search";
    }
}
