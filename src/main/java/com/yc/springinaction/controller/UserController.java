package com.yc.springinaction.controller;

import com.yc.springinaction.model.Authority;
import com.yc.springinaction.model.User;
import com.yc.springinaction.service.AuthorityService;
import com.yc.springinaction.service.UserService;
import com.yc.springinaction.util.ConstraintViolationExceptionHandler;
import com.yc.springinaction.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    /**
     * 获取用户id后跳转至修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String toEdit(@PathVariable("id") Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/users/edit";
    }

    /**
     * 接受用户修改信息，存入数据库
     * @param user
     * @param authorityId
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public String editUser(User user, Long authorityId){
        User oriUser = userService.getUserById(user.getId());
        user.setPassword(oriUser.getPassword());
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(authorityId));
        user.setAuthorities(authorities);
        try{
            userService.saveUser(user);
        }catch (Exception e){
            return "error";
        }
        return user.toString();
    }

    /**
     * 转到add页面
     * @return
     */
    @GetMapping("/toAdd")
    public String toAddPage(){
        return "users/add";
    }

    /**
     * 由管理员添加一个新用户
     * @param user
     * @return
     */
    @PostMapping("/add")
    public String addNewUser(User user, Long authorityId, Model model){

        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(authorityId));
        user.setAuthorities(authorities);
        String password = user.getPassword();
        password = new BCryptPasswordEncoder().encode(password);
        user.setPassword(password);
        try{
            userService.saveUser(user);
        }catch (ConstraintViolationException e){
            String message = ConstraintViolationExceptionHandler.getMessage(e);
            model.addAttribute("addError", true);
            model.addAttribute("errorMsg", message);
            return "/users/add";
        }catch (Exception e){
            String message = e.getMessage();
            model.addAttribute("addError", true);
            model.addAttribute("errorMsg", message);
        }
        return "redirect:/admins/list";
    }

//    @DeleteMapping("/{id}")
//    @ResponseBody
//    public String deleteUser(@PathVariable("id") Long id){
//        User user = userService.getUserById(id);
//        try {
//            userService.removeUser(user);
//        }catch (Exception e){
//            return e.getMessage();
//        }
//
//        return "ok";
//    }
//
//    @GetMapping("toIndex")
//    public String toIndex(){
//        return "/index";
//    }
//
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        try {
            userService.removeUser(user);
        }catch (Exception e){
            return "redirect:/users/list";
        }

        return "redirect:/admins/list";
    }



}
