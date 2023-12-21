package com.bs.firstboot.common;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.debug("--------------- interceptor 시작 -------------------");
		log.debug(request.getRequestURI());
		log.debug("--------------- interceptor 끝 -------------------");
		
		
		return true;
	}
	
	
}
