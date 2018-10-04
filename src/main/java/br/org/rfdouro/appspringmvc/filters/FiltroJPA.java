package br.org.rfdouro.appspringmvc.filters;

import br.org.rfdouro.appspringmvc.helpers.EntityManagerHelper;
import br.org.rfdouro.appspringmvc.util.Util;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * não usada a annotation WebFiler pois o mesmo será adicionado na classe
 * WebInitializer
 *
 * @author romulo.douro
 */
//@WebFilter(servletNames = {"dispatcher"})
//@WebFilter(urlPatterns = "/*")
public class FiltroJPA implements Filter {

 //private int customSessionExpiredErrorCode = 901;
 public static boolean isDebug = true;

 @Override
 public void init(FilterConfig filterConfig) throws ServletException {
  try {
   EntityManagerHelper.inicializaDados(filterConfig.getServletContext());
  } catch (Exception ex) {

  }
 }

 @Override
 public void destroy() {
  EntityManagerHelper.closeEntityManagerFactory();
 }

 @Override
 public void doFilter(ServletRequest request, ServletResponse response,
         FilterChain chain) throws IOException, ServletException {
  
  HttpServletRequest req = (HttpServletRequest) request;
  String usuLogado = (String) req.getSession().getAttribute("USULOGADO");

  //aqui é para pegar o login por cookie
  if (usuLogado == null) {
   Cookie cLogin = Util.getCookie(req, "login");
   Cookie cSenha = Util.getCookie(req, "senha");
   if (cLogin != null && cSenha != null) {
    usuLogado = (String) cLogin.getValue();
    usuLogado = Util.decode(usuLogado);
    req.getSession().setAttribute("USULOGADO", usuLogado);
   }
  }

  String msEX = "";
  EntityManager manager = EntityManagerHelper.getEntityManager(request.getServletContext());
  if (manager != null) {
   request.setAttribute("EntityManager", manager);
   try {
    EntityManagerHelper.beginTransaction(manager);

    chain.doFilter(request, response);

    EntityManagerHelper.commit(manager);

   } catch (Exception e) {
    msEX = "ERRO commit = " + Util.getMsgErro(e);
    try {
     EntityManagerHelper.rollback(manager);
    } catch (Exception ex) {
     msEX += "ERRO rollback = " + Util.getMsgErro(ex);
    }
   } finally {
    try {
     EntityManagerHelper.closeEntityManager(manager);
    } catch (Exception exf) {
     msEX += "ERRO close = " + Util.getMsgErro(exf);
    }
    if (!msEX.equals("")) {
     throw new ServletException(msEX);
    }
   }
  } else {
   throw new ServletException("ERRO DE ACESSO AO BANCO DE DADOS");
  }
 }
}
