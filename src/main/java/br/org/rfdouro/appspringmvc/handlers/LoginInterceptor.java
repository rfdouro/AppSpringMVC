/*
 * -----------------
 * -----------------
 * -----------------
 */
package br.org.rfdouro.appspringmvc.handlers;

import br.org.rfdouro.appspringmvc.annotations.VerificaAcesso;
import br.org.rfdouro.appspringmvc.util.Util;
import java.lang.reflect.Method;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author romulo.douro
 *
 * http://www.concretepage.com/spring/spring-mvc/spring-handlerinterceptor-annotation-example-webmvcconfigureradapter
 * https://www.mkyong.com/spring-mvc/spring-mvc-handler-interceptors-example/
 * http://www.baeldung.com/spring-mvc-handlerinterceptor
 *
 * http://www.myjavarecipes.com/tag/spring-mvc-interceptor-annotation-example/
 */
public class LoginInterceptor implements HandlerInterceptor {

 public boolean verificaLogin(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
  HandlerMethod hm = (HandlerMethod) handler;
  Method method = hm.getMethod();
  if (method.getDeclaringClass().isAnnotationPresent(Controller.class)) {
   if (method.isAnnotationPresent(VerificaAcesso.class)) {

    String[] ids = method.getAnnotation(VerificaAcesso.class).value();

    boolean verificado = false;
    for (String idVerifica : ids) {
     if (Util.verificaAcesso(request, idVerifica)) {
      verificado = true;
      break;
     }
    }

    if (!verificado) {
     request.setAttribute("javax.servlet.error.status_code", 401);
     RequestDispatcher rd = request.getRequestDispatcher("/errors");
     rd.forward(request, response);
     return false;
    }
   }
  }
  return true;
 }

 @Override
 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
         throws Exception {
  //System.out.println("---Before Method Execution---");
  request.getSession().setAttribute("USULOGADO", "usuario");
  return true;
 }

 @Override
 public void postHandle(HttpServletRequest request, HttpServletResponse response,
         Object handler, ModelAndView modelAndView) throws Exception {
  //System.out.println("---method executed---");
  System.out.println("" + request.getSession().getAttribute("USULOGADO"));
  boolean config = Boolean.parseBoolean(Util.getValorPropriedade(request.getServletContext(), "config"));
  if (!config && (handler instanceof HandlerMethod)) {
   this.verificaLogin(request, response, handler);
  }
 }

 @Override
 public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
         Object handler, Exception ex) throws Exception {
  //System.out.println("---Request Completed---");
 }
}
