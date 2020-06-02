package com.kyoborealco.kreps.zsite.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 로그인 처리시 화면에서 전달되는 정보 모델 class 
 *
 */
@Data
public class LoginUser {
	
	@Size(min=2, message="{common.hangulName.min.message}")
	private String username;				// 사용자ID
	
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$", message="{job.noticePwd.pattern.message}") //  8 ~ 10자 영문, 숫자 조합
	private String password;				// 패스워드
	
	private String name;					// 사용자명
	
	private String recaptchaToken;			// recaptcha token
}
