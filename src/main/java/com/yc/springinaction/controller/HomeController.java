package com.yc.springinaction.controller;

import com.yc.springinaction.model.Authority;
import com.yc.springinaction.model.User;
import com.yc.springinaction.service.AuthorityService;
import com.yc.springinaction.service.UserService;
import com.yc.springinaction.util.ConstraintViolationExceptionHandler;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private static final Long ROLE_USER_AUTHORITY_ID = 2L;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String toIndex(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("login")
    public String login(){
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

    @PostMapping("/register")
    public String register(User user, Model model){
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
        user.setAuthorities(authorities);
        String password = user.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        user.setPassword(password);
        try {
            userService.saveUser(user);
        }catch (ConstraintViolationException e){
            String message = ConstraintViolationExceptionHandler.getMessage(e);
            model.addAttribute("isError", true);
            model.addAttribute("errorMsg", message);
            return "/register";
        }


        return "redirect:/login";
    }

    @GetMapping("/search")
    public String search(){
        return "search";
    }

    @GetMapping("/status")
    public String status(){
        return "status";
    }


    @PostMapping("/setStatus")
    public ModelAndView toIndex(@RequestParam("input") String input, Model model){
        System.out.println(input);
        model.addAttribute("input", input);
        return new ModelAndView("status", "status", model);
    }
}
