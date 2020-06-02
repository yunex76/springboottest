package com.kyoborealco.kreps.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.kyoborealco.kreps.auth.KrepsAuthenticationProvider;

import lombok.RequiredArgsConstructor;

/**
 * 홈페이지 보안 인증 설정 configuration 클래스
 * 
 * 
 * @see https://www.baeldung.com/spring-security-two-login-pages 
 * @see https://stackoverflow.com/questions/52647148/multiple-login-pages-spring-security-using-spring-boot (다중 로그인화면 구분처리)
 * @see https://stackoverflow.com/questions/43675208/spring-security-multiple-entry-points-login-process-url-not-found (login process 핉터체인 설정)
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @RequiredArgsConstructor
    @Configuration
    @Order(1)
    public static class CooperConfigurationAdapter extends WebSecurityConfigurerAdapter {

    	private final UserDetailsService userDetailsService;
    	private final KrepsAuthenticationProvider authenticationProvider;
        private final PasswordEncoder passwordEncoder;
        
    	@Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    		auth
            	.authenticationProvider(authenticationProvider)
            	.userDetailsService(userDetailsService)
            	.passwordEncoder(passwordEncoder);
        }
    	
    	
    	@Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
            	.antMatchers("/resources/**")
            	.antMatchers("/css/**", "/js/**", "/webfonts/**", "/img/**", "/media/**");
        }
    	
    	@Override
    	protected void configure(HttpSecurity http) throws Exception {
    		
    		http.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/page_login").hasRole("VENDOR")
				.anyRequest()
				.authenticated()
    		.and()
    			.formLogin()
    				.loginPage("/page_login")
    				.loginProcessingUrl("/login")
						.permitAll()
    				.failureUrl("/page_login?error=loginError")
    				.defaultSuccessUrl("/intel/marketing_dashboard/")
    		.and()
    			.logout()
    				.invalidateHttpSession(true)
                    .clearAuthentication(true)
    				.logoutUrl("/logout")
    				.logoutSuccessUrl("/page_login")
    				.deleteCookies("JESSIONID")
    		.and()
    			.exceptionHandling()
    				.accessDeniedPage("/error_403")
    	    .and()
               	.csrf().disable();    				
    	}	
    }

}
