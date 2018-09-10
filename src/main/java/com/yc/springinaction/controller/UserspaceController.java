package com.yc.springinaction.controller;

import com.yc.springinaction.model.User;
import com.yc.springinaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/u")
public class UserspaceController {
    private static final String UPLOAD_PATH = "/Users/yc/code/springUpload";

    @Autowired
    private UserService userService;

    /**跳转至个人信息主页，所有人能够查看
     * 跳转至
     * @param username
     * @return
     */
    @GetMapping("/{username}")
    public String userSpace(@PathVariable("username") String username, Model model){
        User user = userService.getUserByUsername(username);
        System.out.println(user.toString());
        model.addAttribute("user", user);
        return "/userspace/personalInfo";
    }

    @GetMapping("/toUpload")
    public String toUpload(){
        return "/userspace/upload";
    }


    @PostMapping("/testAPI")
    @ResponseBody
    public String tstAPI(@RequestParam("api") String api){
        String response = api + " upload";
        return response;
    }

    @PostMapping("/upload")
    public String uploadTest(@RequestParam("file") MultipartFile file, Model model){
        if (file.isEmpty()){
            model.addAttribute("message", "choose a file.");
        }
        else {
            String messqge =  "getName():" + file.getName() + " getOdiginalFilename \n " + file.getOriginalFilename()
            + " \n contentType: " + file.getContentType();
            model.addAttribute("message", messqge);
        }
        return "/userspace/uploadStatus";
    }

//    @PostMapping("/up")
//    public String test(@RequestParam("input") String input, RedirectAttributes redirectAttributes){
//        String message = input + " upload fuck";
//
//        redirectAttributes.addAttribute("message", message);
//        return "redirect:/userspace/uploadStatus";
//    }

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
