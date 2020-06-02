package com.kyoborealco.kreps.zsite.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.kyoborealco.kreps.model.entity.Users;
import com.kyoborealco.kreps.repository.UserLoginRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Spring Security UserDetailsService 구현 클래스
 *
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class UserLoginDetailService implements UserDetailsService {

	@NonNull
	private final UserLoginRepository userLoginRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		final Users userLogin = userLoginRepository.findById(username).orElse(null);
		
		if (userLogin == null) {
			throw new UsernameNotFoundException("USER_NOT_FOUND");
		}
		
		/**
		 * 입주사(TENANT), 협력업체(COOPER), 채용지원자(RECRUIT)는 고유의 역할(ROLE)을 1개씩 보유하고 있음.
		 * ==============================================================================
		 * 관리자(ADMIN)은 별도의 역할권한 테이블 정보를 가지고 있으므로 
		 * 현행 프로세스로는 사용자화면에서는 역할(ROLE) 설정이 되지 않음.
		 */
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//		if (userLogin.getRole() != null) {
//			grantedAuthorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", userLogin.getRole())));			
//		}
//		
//		log.debug("★★★★★ authorities ==> {}", grantedAuthorities.toString());
		
		return org.springframework.security.core.userdetails
			    .User
			    	.withUsername(userLogin.getUserId())
					.password(userLogin.getPassword())
					.authorities(grantedAuthorities)
					.accountExpired(false)
					.accountLocked(false)
					.credentialsExpired(false)
					.disabled(false)
					.build();	}

}
