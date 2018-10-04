/*
 * -----------------
 * -----------------
 * -----------------
 */
package br.org.rfdouro.appspringmvc.config;

import br.org.rfdouro.appspringmvc.filters.FiltroJPA;
import br.org.rfdouro.appspringmvc.helpers.EntityManagerHelper;
import br.org.rfdouro.appspringmvc.listeners.SessionListener;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author romulo.douro
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

 private String TMP_FOLDER = "C://temp//";
 private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;

 @Override
 protected Class<?>[] getRootConfigClasses() {
  return null;
 }

 @Override
 protected Class<?>[] getServletConfigClasses() {
  return new Class[]{Config.class};
 }

 @Override
 protected String[] getServletMappings() {
  return new String[]{"/"};
 }

 @Override
 public void onStartup(ServletContext servletContext) throws ServletException {
  AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
  ctx.register(Config.class);
  ctx.setServletContext(servletContext);
  ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
  servlet.addMapping("/");
  servlet.setLoadOnStartup(1);

  EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
  //adiciona o filtro para o JPA
  FilterRegistration.Dynamic JPAFilter = servletContext.addFilter("JPAFilter", FiltroJPA.class);
  //JPAFilter.addMappingForServletNames(null, true, "dispatcher");
  //JPAFilter.addMappingForUrlPatterns(null, true, "/*");
  JPAFilter.addMappingForUrlPatterns(dispatcherTypes, true, "/*");

  //ouvinte de sessões  (caso exista)
  servletContext.addListener(new SessionListener());
  MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER,
          MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
  servlet.setMultipartConfig(multipartConfigElement);

  try {
   EntityManagerHelper.inicializaDados(servletContext);
  } catch (Exception ex) {
   String m = br.org.rfdouro.appspringmvc.util.Util.getMsgErro(ex);
   System.out.println(m);
  }
 }

}
