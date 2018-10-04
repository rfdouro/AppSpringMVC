package br.org.rfdouro.appspringmvc.listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author romulo.douro
 */
public class RequestListener implements ServletRequestListener {

 public void requestDestroyed(ServletRequestEvent event) {

 }

 public void requestInitialized(ServletRequestEvent event) {
  String x = ((HttpServletRequest) event.getServletRequest()).getRemoteUser();
  System.out.println("" + x);
  System.out.println("sessão = " + ((HttpServletRequest) event.getServletRequest()).getSession().isNew());
 }

}
