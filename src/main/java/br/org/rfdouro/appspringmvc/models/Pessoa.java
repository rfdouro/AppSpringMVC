
package br.org.rfdouro.appspringmvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author romulo.douro
 */
@Entity
public class Pessoa {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id;
 private String nome;

 public long getId() {
  return id;
 }

 public void setId(long id) {
  this.id = id;
 }

 public String getNome() {
  return nome;
 }

 public void setNome(String nome) {
  this.nome = nome;
 }
 
 
}
