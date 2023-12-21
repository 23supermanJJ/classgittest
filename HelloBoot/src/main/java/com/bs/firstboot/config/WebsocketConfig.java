package com.bs.firstboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.bs.firstboot.common.ChattingServer;

@Configuration
// 구성되는 파일
@EnableWebSocket
// 세팅된대로 websocket 움직이겠다~~!!
public class WebsocketConfig implements WebSocketConfigurer {

	
	@Autowired
	private ChattingServer chatserver;
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		// 메소드 방식으로 handler 등록
		
		registry.addHandler(chatserver, "/chat");
		// websocket handler, websocket 경로
		
	}
	
	

}
