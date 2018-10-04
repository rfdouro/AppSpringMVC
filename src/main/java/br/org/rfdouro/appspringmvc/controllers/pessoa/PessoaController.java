package br.org.rfdouro.appspringmvc.controllers.pessoa;

import br.org.rfdouro.appspringmvc.annotations.VerificaAcesso;
import br.org.rfdouro.appspringmvc.controllers.DefaultController;
import br.org.rfdouro.appspringmvc.models.Pessoa;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author romulo.douro
 */
@Controller
@RequestMapping(value = "/pessoa")
public class PessoaController extends DefaultController {

 @VerificaAcesso(value = "pessoas")
 @RequestMapping(value = {"", "/", "/index", "/lista"}, method = RequestMethod.GET)
 public String lista(Model model) {
  this.setRepositorio();
  List<Pessoa> l = this.repositorio.buscaTodos(Pessoa.class);
  model.addAttribute("lista", l);
  return "pessoa/lista";
 }
 
 @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
 public String cadastra(Model model, @RequestParam(value = "nome", required = true) String nome) {
  this.setRepositorio();
  Pessoa p = new Pessoa();
  p.setNome(nome);
  this.repositorio.adiciona(p, false);
  return lista(model);
 }

}
