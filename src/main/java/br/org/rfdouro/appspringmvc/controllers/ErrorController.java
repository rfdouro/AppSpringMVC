/*
 * 
 * 
 * 
 */
package br.org.rfdouro.appspringmvc.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author romulo.douro based in
 * http://www.baeldung.com/custom-error-page-spring-mvc
 */
@Controller
public class ErrorController {

 @RequestMapping(value = "/errors", method = RequestMethod.GET)
 public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

  ModelAndView errorPage = new ModelAndView("errorPage");
  errorPage.addObject("tituloInternoPagina", "ERRO");
  String errorMsg = "Erro genérico";
  int httpErrorCode = getErrorCode(httpRequest);

  switch (httpErrorCode) {
   case 400: {
    errorMsg = "Requisição ruim ou danificada";
    break;
   }
   case 401: {
    errorMsg = "Não autorizado";
    break;
   }
   case 404: {
    errorMsg = "Recurso ou Página não encontrada";
    break;
   }
   case 500: {
    Throwable t = (Throwable) httpRequest.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
    String msgCausa = "";
    if (t != null) {
     msgCausa = "<br/>" + t.getMessage();
     while (t.getCause() != null) {
      t = t.getCause();
      msgCausa += "<br/>" + t.getMessage();
     }
    }
    errorMsg = "Erro interno de servidor " + msgCausa;
    break;
   }
  }
  errorPage.addObject("errorMsg", errorMsg);
  errorPage.addObject("httpErrorCode", httpErrorCode);
  errorPage.addObject("MODULO", "<li class=\"active\">ERRO</li>");
  return errorPage;
 }

 private int getErrorCode(HttpServletRequest httpRequest) {
  return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
 }
}
