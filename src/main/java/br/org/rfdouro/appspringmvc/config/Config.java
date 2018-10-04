/*
 * -----------------
 * -----------------
 * -----------------
 */
package br.org.rfdouro.appspringmvc.config;

import br.org.rfdouro.appspringmvc.handlers.LoginInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author romulo.douro
 *
 * https://www.boraji.com/spring-mvc-5-static-resources-handling-example
 */
@Configuration
@ComponentScan("br.org.rfdouro.appspringmvc")
@EnableWebMvc
public class Config implements WebMvcConfigurer {

 @Override
 public void configureViewResolvers(ViewResolverRegistry registry) {
  //registry.jsp("/WEB-INF/jsp/", ".jsp");
  InternalResourceViewResolver resolver = new InternalResourceViewResolver();
  resolver.setPrefix("/WEB-INF/jsp/");
  resolver.setSuffix(".jsp");
  resolver.setViewClass(JstlView.class);
  registry.viewResolver(resolver);
 }

 @Override
 public void addResourceHandlers(ResourceHandlerRegistry registry) {
  // Register resource handler
  registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");//.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
 }

 @Override
 public void addInterceptors(InterceptorRegistry registry) {
  registry.addInterceptor(new LoginInterceptor())
          //cada modulo deve ser adicionado aqui
          .addPathPatterns("/pessoa/**")
          .excludePathPatterns("/**/login/**");
  /*registry.addInterceptor(new LoginInterceptorWS())
          .addPathPatterns("/webservice/minhasVendas/**")
          .excludePathPatterns("/webservice/login/**");*/
 }

 /**
  * https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
  *
  * @param registry
  */
 @Override
 public void addCorsMappings(CorsRegistry registry) {
  //registry.addMapping("/webservice/**");
  registry.addMapping("/**");
 }

}
