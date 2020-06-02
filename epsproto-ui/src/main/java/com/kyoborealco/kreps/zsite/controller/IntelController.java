package com.kyoborealco.kreps.zsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Intel 컨트롤러 클래스 
 *
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class IntelController {

	@GetMapping("/intel/introduction/")
    public String getIntelIntroduction(Model model) {
    	
    	log.debug("★★★★★ intel introduction controller ==> {}", "intel_introduction");
    	
        return "/view/intel/introduction";
    }
	
	@GetMapping("/intel/analytics_dashboard/")
    public String getIntelAnalyticsDashboard(Model model) {
    	
    	log.debug("★★★★★ intel analytics_dashboard controller ==> {}", "intel_analytics_dashboard");
    	
        return "/view/intel/analytics_dashboard";
    }
	
	@GetMapping("/intel/marketing_dashboard/")
    public String getIntelMarketingDashboard(Model model) {
    	
    	log.debug("★★★★★ intel marketing_dashboard controller ==> {}", "intel_marketing_dashboard");
    	
        return "/view/intel/marketing_dashboard";
    }
	
}
