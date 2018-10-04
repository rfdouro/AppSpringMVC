/*
 * -----------------
 * -----------------
 * -----------------
 */
package br.org.rfdouro.appspringmvc.controllers;

import br.org.rfdouro.appspringmvc.persistence.Repositorio;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author romulo.douro
 */
public abstract class DefaultController {

 @Autowired
 protected HttpServletRequest request;
 protected EntityManager manager;
 protected Repositorio repositorio;
 @Autowired
 protected HttpSession session;

 protected EntityManager getEntityManager() {
  EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
  return manager;
 }

 protected void setRepositorio() {
  this.manager = this.getEntityManager();
  if (!this.manager.getTransaction().isActive()) {
   this.manager.getTransaction().begin();
  }
  this.repositorio = new Repositorio(manager) {
  };
 }
}
