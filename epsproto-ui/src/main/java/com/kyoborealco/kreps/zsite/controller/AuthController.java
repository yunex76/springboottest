package com.kyoborealco.kreps.zsite.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kyoborealco.kreps.zsite.dto.LoginUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AuthController {

    @GetMapping("/page_login")
    public String loginCooper(Model model, HttpServletRequest request) {
    	
    	log.debug("★★★★★ auth controller ==> {}", "loginCooper");

    	request.getSession().invalidate();
    	
    	LoginUser loginForm = new LoginUser();
    	
    	model.addAttribute("loginForm", loginForm);
    	
        return "page_login";
    }
    
	/**
	 * 협력업체 접근오류(403)
	 * @return
	 */
    @GetMapping("/error_403")
    public String accessDenied(Model model) {
    	
    	log.debug("★★★★★ auth controller ==> {}", "accessDenied");
    	
        return "page_error_403";
    }
	
    
    /**
	 * Error발생시 redirect => error
	 * @return
	 */
    @GetMapping("/error")
    public String error(Model model) {
    	
    	log.debug("★★★★★ error redirect to 'error' ==> {}", "error");
    	
        return "page_error";
    }
}
