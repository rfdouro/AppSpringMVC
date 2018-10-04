/*
 * -----------------
 * -----------------
 * -----------------
 */
package br.org.rfdouro.appspringmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author romulo.douro
 */
@Controller
public class HomeController extends DefaultController {
 
 @RequestMapping(value = {"/home", "/home/index"})
 public String page(Model model) {
  model.addAttribute("mensagem", "Index de home");
  return "home/index";
 }
 
}
