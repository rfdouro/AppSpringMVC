package br.org.rfdouro.appspringmvc.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author romulo.douro
 */
public class SessionListener implements HttpSessionListener {

 private static int totalActiveSessions;
 private static int totalUsuLogado;

 public static int getTotalActiveSession() {
  return totalActiveSessions;
 }
 
 public static int getTotalUsuLogado() {
  return totalUsuLogado;
 }
 
 public static void incTotalUsuLogado() {
  totalUsuLogado ++;
 }

 @Override
 public void sessionCreated(HttpSessionEvent arg) {
  totalActiveSessions++;
  System.out.println("Sessão criada - adiciona uma sessão ao contador");
 }

 @Override
 public void sessionDestroyed(HttpSessionEvent arg) {
  totalActiveSessions--;
  System.out.println("Sessão destruída - reduz uma sessão do contador");
 }
 

}
