package com.kyoborealco.kreps.auth;

import javax.inject.Inject;

import org.apache.commons.math3.exception.NullArgumentException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * password match처리를 위해  AuthenticationProvider custom구현
 * legacy 인코딩 패스워드와 신규(spring security 5) 인코딩 패스워드 병행 적용 위해
 *
 */
@Slf4j
@Component
public class KrepsAuthenticationProvider implements AuthenticationProvider {

	private final UserDetailsService udsService;
	private final PasswordEncoder passwordEncoder;
	
	@Inject
	public KrepsAuthenticationProvider(UserDetailsService userDetailsService) {
		super();
	
		this.udsService = userDetailsService;
		this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		/*
		 * if (this.passwordEncoder == null) { this.passwordEncoder =
		 * PasswordEncoderFactories.createDelegatingPasswordEncoder(); }
		 */
		
		String username = (String) authentication.getPrincipal(); // UserLoginID
	    String password = (String) authentication.getCredentials();
	    
	    log.debug("★★★★★ username {}, password {}", username, password);
	    
	    if (username == null || username.isEmpty()) {
	    	throw new NullArgumentException();
	    }
	    
	    UserDetails user = udsService.loadUserByUsername(username);
	    
	    if (!passwordEncoder.matches(password, user.getPassword())) {
	    	throw new BadCredentialsException(username);
	    }
		
		return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
