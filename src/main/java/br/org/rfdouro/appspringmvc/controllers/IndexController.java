/*
 * -----------------
 * -----------------
 * -----------------
 */
package br.org.rfdouro.appspringmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author romulo.douro
 */
@Controller
public class IndexController extends DefaultController {
 
 @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
 public String page(Model model) {
  model.addAttribute("mensagem", "Página inicial");
  return "index";
 }
 
}
