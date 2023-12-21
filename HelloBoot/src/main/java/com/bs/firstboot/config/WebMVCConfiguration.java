package com.bs.firstboot.config;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.bs.firstboot.common.LoggerInterceptor;
import com.bs.firstboot.model.dto.Member;

@Configuration //webMvcConfigurer인터페이스
@EnableAspectJAutoProxy //aop가 정상적으로 작동할수 있는거임
@MapperScan("com.bs.firstboot.common.mapper")
public class WebMVCConfiguration implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 인터셉터등록할 때 사용하는 메소드
		registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// view 연결해주는 로직을 코드로 작성할 때 사용
		registry.addViewController("/board/boardlist").setViewName("board/boardlist");
		registry.addViewController("/chatpage").setViewName("chatting");
	}
	
	@Bean
	LoggerInterceptor loggerInter() {
		return new LoggerInterceptor();
	}	
	
	@Bean
	public Member member() {
		return new Member();
	}
	
	@Bean
	@Primary
	HandlerExceptionResolver handleExceptionResolver() {
		Properties exceptionProp = new Properties();
		exceptionProp.setProperty(IllegalArgumentException.class.getName(), "error/argumentException");
		SimpleMappingExceptionResolver resolve = new SimpleMappingExceptionResolver();
		resolve.setExceptionMappings(exceptionProp);
		resolve.setDefaultErrorView("error/error");
		return resolve;
	}
	
	// 외부에서 js로 요청한 것에 대해 허용하기
	// cors : 다른 서버에서 Js로 요청한 내용을 차단함
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:3000");
		// localhost:3000에서 오는 요청을 다 받겠다! 
		// 나랑 origin이 다르더라도..!
		
	}
	

	
}
