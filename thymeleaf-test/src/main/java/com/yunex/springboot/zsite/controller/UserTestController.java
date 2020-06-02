package com.yunex.springboot.zsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yunex.springboot.entity.User;

@Controller
public class UserTestController {

    @GetMapping("/test")
    public String getUser(Model model) {
        User user = new User("kkaok", "테스트", "web") ;
        model.addAttribute("user", user);
        return "test";
    }
}
