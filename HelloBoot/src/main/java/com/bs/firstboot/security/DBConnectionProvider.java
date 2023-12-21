package com.bs.firstboot.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.bs.firstboot.common.mapper.MemberMapper;
import com.bs.firstboot.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DBConnectionProvider implements AuthenticationProvider{
	
	private final MemberMapper dao;
	private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 인증할 때 id, pw에 대한 정보를 줌
		String userId = authentication.getName();
		String password=(String)authentication.getCredentials();
		// 비밀번호가 그냥 비밀번호일 수 있고 다른 거 일 수 있어서... 암호화.등.. .// object로 반환함.
		
		Member loginMember=dao.selectMemberById(userId);
		if(loginMember==null || 
				!encoder.matches(password,loginMember.getPassword())) {
			throw new BadCredentialsException("인증실패!");
		}
		return new UsernamePasswordAuthenticationToken(loginMember,
				loginMember.getPassword(),loginMember.getAuthorities());
	}


	@Override
	public boolean supports(Class<?> authentication) {
		// 
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
		// id,pw인증받을 때 이 토큰을 이용하겠다.!!
	}
	
	

}
