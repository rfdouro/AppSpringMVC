package br.org.rfdouro.appspringmvc.controllers.login;

import br.org.rfdouro.appspringmvc.controllers.DefaultController;
import br.org.rfdouro.appspringmvc.util.Util;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author romulo.douro
 */
@Controller
@RequestMapping("/login")
public class LoginController extends DefaultController {

 @RequestMapping(value = {"", "/"})
 public ModelAndView login(ModelMap map) {
  map.addAttribute("tituloInternoPagina", "Comissione [Login]");
  map.addAttribute("MODULO", "<li class=\"active\">Comissione</li><li class=\"Login\">Módulos</li>");

  return new ModelAndView("login/login", map);
 }

 @RequestMapping(value = {"/loga"})
 public String loga(Model map, String login, String senha,
         @RequestParam(required = false) String redir,
         HttpServletResponse response,
         HttpServletRequest request) {
  String pagina = "forward:/login";
  map.addAttribute("redir", redir);

  this.setRepositorio();
  if (login != null && senha != null && !senha.equals("")) {

   this.session.setAttribute("USULOGADO", login);

  }
  pagina = "redirect:/home";
  if (redir != null && !redir.equals("")) {
   pagina = "redirect:" + redir;
  }
  return pagina;
 }

 @RequestMapping(value = {"/confirmaDesloga"})
 public String confirmaDesloga(Model map, HttpServletResponse response) {
  String pagina = "redirect:/";

  //request.getSession().setAttribute("USULOGADO", null);
  this.session.setAttribute("USULOGADO", null);

  {
   Cookie cookie = new Cookie("login", null);
   //cookie.setPath("/");
   cookie.setPath(request.getContextPath());//importantíssimo
   cookie.setMaxAge(0);//0
   response.addCookie(cookie);
   cookie = new Cookie("senha", null);
   //cookie.setPath("/");
   cookie.setPath(request.getContextPath());//importantíssimo
   cookie.setMaxAge(0);//0
   response.addCookie(cookie);
  }

  return pagina;
 }

}
