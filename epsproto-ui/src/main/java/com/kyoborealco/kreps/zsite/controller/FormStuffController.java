package com.kyoborealco.kreps.zsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Form-Stuff 컨트롤러 클래스 
 *
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class FormStuffController {

	@GetMapping("/form_stuff/basic_inputs/")
    public String getFormBasicInputs(Model model) {
    	
    	log.debug("★★★★★ form stuff - basic inputs controller ==> {}", "form_basic_inputs");
    	
        return "/view/form/basic_inputs";
    }
	
	@GetMapping("/form_stuff/validation/")
    public String getFormValidation(Model model) {
    	
    	log.debug("★★★★★ form stuff - validation controller ==> {}", "form_validation");
    	
        return "/view/form/validation";
    }
	
}
