/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.rfdouro.appspringmvc.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 *
 * @author aluno2
 * @see https://www.boraji.com/spring-mvc-5-handling-websocket-message-example
 */
@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

 @Override
 protected void handleTextMessage(WebSocketSession session, TextMessage message)
         throws Exception {

  String clientMessage = message.getPayload();

  if (clientMessage.startsWith("Hello") || clientMessage.startsWith("Hi")) {
   session.sendMessage(new TextMessage("Hello! What can i do for you?"));
  } else {
   session.sendMessage(
           new TextMessage("This is a simple hello world example of using Spring WebSocket."));
  }
 }

}
