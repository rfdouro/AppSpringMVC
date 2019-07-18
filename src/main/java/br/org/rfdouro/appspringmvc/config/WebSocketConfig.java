/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.rfdouro.appspringmvc.config;

import br.org.rfdouro.appspringmvc.websocket.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 *
 * @author aluno2
 */
@Configuration
@EnableWebSocket
@ComponentScan("br.org.rfdouro.appspringmvc.websocket")
public class WebSocketConfig implements WebSocketConfigurer {

 @Autowired
 private MyWebSocketHandler myWebSocketHandler;

 @Override
 public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
  registry.addHandler(myWebSocketHandler, "/socketHandler");
 }

}
