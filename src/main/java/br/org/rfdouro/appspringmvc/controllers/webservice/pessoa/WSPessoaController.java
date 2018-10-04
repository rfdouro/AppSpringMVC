/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.rfdouro.appspringmvc.controllers.webservice.pessoa;

import br.org.rfdouro.appspringmvc.annotations.VerificaAcesso;
import br.org.rfdouro.appspringmvc.controllers.DefaultController;
import br.org.rfdouro.appspringmvc.models.Pessoa;
import br.org.rfdouro.appspringmvc.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author romulo.douro
 */
@RestController
@RequestMapping(value = "/ws/pessoa")
public class WSPessoaController extends DefaultController {

 @VerificaAcesso(value = "pessoa")
 @GetMapping(value = {"", "/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
 public String page(@PathVariable(value = "id", required = false) Long id) {
  String ret = "";
  ObjectMapper objectMapper = new ObjectMapper();
  this.setRepositorio();
  try {
   if (id == null) {
    List l = this.repositorio.buscaGenerica("select p from Pessoa p order by p.nome");
    ret = objectMapper.writeValueAsString(l);
   }else{
    Object o = this.repositorio.get(id, Pessoa.class);
    ret = objectMapper.writeValueAsString(o);
   }
  } catch (Exception ex) {
   ret = Util.getMsgErro(ex);
  }
  return ret;
 }

}
