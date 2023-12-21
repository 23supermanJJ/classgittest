package com.bs.firstboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

import com.bs.firstboot.common.MyAuthority;
import com.bs.firstboot.security.DBConnectionProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// SecurityFilterChain 클래스를 bean으로 등록
//	@SuppressWarnings("dep")
	
	
	@Autowired
	DBConnectionProvider dbprovider;
	
	
	@Bean
	SecurityFilterChain authenticationPath(HttpSecurity http) throws Exception {
		// HttpSecurity : 레거시에서 security:http.... 로그인폼.. 똑같음! 
		return http.csrf(csrf->csrf.disable())
						.authorizeHttpRequests(request->{
							// request.requestMatchers("/**").permitAll();
							// main은 무조건 허용하겠다! 
							// request.requestMatchers("/").permitAll();
							 // request.anyRequest().authenticated();
							// 어떤 요쳥이든 권한이 있어야해!
							// 엑세스가 거부됨 -> 권한 체크하는 중 -> security가 막음 ㅜ
							request.requestMatchers("/").permitAll()
							// jsp 넘기는 것도 차단해버림.
						.requestMatchers(req->CorsUtils.isPreFlightRequest(req)).permitAll()
								//CorsUtils::isPreFlightRequest).permitAll()
						.requestMatchers("/WEB-INF/views/**").permitAll()
						.requestMatchers("/members")
						.hasAnyAuthority(MyAuthority.ADMIN.name())
						.anyRequest().authenticated();
						})
						.formLogin(formlogin->{
							formlogin.loginProcessingUrl("/logintest");
//							.failureForwardUrl("/loginfail")
//							.successForwardUrl("/loginsucess");
						})
						.logout(logout->logout.logoutUrl("/logout"))
							// formlogin.loginPage("/loginpage");
						.authenticationProvider(dbprovider)
						// 인증 / db와 연동해서 인증 받기
//						.csrf()
//							.disable()
//						.formLogin()
//							.loginPage("")
//							.successForwardUrl(null)
						.build();
		
	}

}
