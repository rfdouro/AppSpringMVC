package br.org.rfdouro.appspringmvc.persistence;

import br.org.rfdouro.appspringmvc.helpers.EntityManagerHelper;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * Classe abstrata que possui métodos de instancia para operações básicas de DAO
 * Também possui métodos estáticos para trabalhar com DAO em um EntityManager
 * que não está presente na instancia Fio deixada abstrata para que pudessem ser
 * extendidos métodos específicos de busca
 *
 * @author romulo.douro
 */
public abstract class Repositorio {

 /**
  * representa o EM para as conexões da JPA
  */
 private EntityManager manager;

 /**
  * método construtor da classe 
  * obriga a ter um EM sempre que criar uma instância
  * @param manager 
  */
 public Repositorio(EntityManager manager) {
  this.manager = manager;
 }

 /**
  * recupera o EM da classe
  * @return 
  */
 public EntityManager getManager() {
  return manager;
 }

 /**
  * atribui o EM da classe
  * inútil já que tem-se apenas o construtor sobrecarregado
  * o que obriga a utilizá-lo
  * @param manager 
  */
 public void setManager(EntityManager manager) {
  this.manager = manager;
 }
 
 
 /**
  * efetua o commit do entity manager
  */
 public void commitEM() {
  EntityManagerHelper.commit(this.manager);
 }

 /**
  * insere um objeto no BD
  * o objeto deve ser uma @Entity
  * @param obj 
  */
 public void adiciona(Object obj, boolean comita) {
  this.manager.persist(obj);
  if(comita)
   EntityManagerHelper.commit(this.manager);
 }
 
 /**
  * exclui uma entidade do BD
  * @param obj entidade a ser excluída
  */
 public void exclui(Object obj, boolean comita) {
  this.manager.remove(obj);
  if(comita)
   EntityManagerHelper.commit(this.manager);
 }

 /**
  * altera uma entidade
  * @param obj entidade a ser alterada
  */
 public void altera(Object obj, boolean comita) {
  this.manager.merge(obj);
  if(comita)
   EntityManagerHelper.commit(this.manager);
 }

 /**
  * método estático que pode ser usado para inserir uma entidade
  * @param obj objeto a ser inserido no BD
  * @param manager EM para a JPA
  * @throws IOException 
  */
 public static void adicionaEstatico(Object obj, EntityManager manager) throws IOException {
  if (!manager.getTransaction().isActive()) {
   EntityManagerHelper.beginTransaction(manager);
  }
  manager.persist(obj);
  EntityManagerHelper.commit(manager);
 }

 /**
  * adiciona vários objetos
  * @param objs objetos a serem inseridos
  */
 /*public void adicionaVarios(Object... objs) {
  EntityManagerHelper.beginTransaction(this.manager);
  for (Object o : objs) {
   this.manager.persist(o);
  }
  EntityManagerHelper.commit(this.manager);
 }*/

 /**
  * método estático para alterar uma entidade
  * @param obj entidade a ser alterada
  * @param manager EM da JPA
  * @throws IOException
  */
 public static void alteraEstatico(Object obj, EntityManager manager) throws IOException {
  if (!manager.getTransaction().isActive()) {
   EntityManagerHelper.beginTransaction(manager);
  }
  manager.merge(obj);
  EntityManagerHelper.commit(manager);
 }

 /**
  * método estático para excluir uma entidade
  * @param obj entidade a ser excluída
  * @param manager EM da JPA
  * @throws IOException
  */
 public static void removeEstatico(Object obj, EntityManager manager) throws IOException {
  if (!manager.getTransaction().isActive()) {
   EntityManagerHelper.beginTransaction(manager);
  }
  manager.remove(obj);
  EntityManagerHelper.commit(manager);
 }

 /**
  * seleciona um objeto pela classe e o ID (Long)
  * @param id PK da entidade
  * @param c classe da entidade
  * @return o objeto da entidade
  */
 public Object get(Long id, Class c) {
  Object o = this.manager.find(c, id);
  return o;
 }

 /**
  * seleciona um objeto pela classe e o ID (Integer)
  * @param id PK da entidade
  * @param c classe da entidade
  * @return o objeto da entidade
  */
 public Object get(Integer id, Class c) {
  Object o = this.manager.find(c, id);
  return o;
 }

 /**
  * 
  * @param id
  * @param c
  * @param manager
  * @return
  * @throws IOException
  */
 public static Object getEstatico(Integer id, Class c, EntityManager manager) throws IOException {
  EntityManagerHelper.beginTransaction(manager);
  Object o = manager.find(c, id);
  EntityManagerHelper.commit(manager);
  return o;
 }

 /**
  *
  * @param c
  * @return
  */
 public List buscaTodos(Class c) {
  String nome = c.getName();
  int indexPonto = nome.lastIndexOf('.');
  if (indexPonto >= 0) {
   nome = nome.substring(indexPonto + 1);
  }
  Query query = this.manager.createQuery("select c from " + nome + " c ");
  return query.getResultList();
 }

 /**
  *
  * @param c
  * @param where
  * @param param
  * @param o
  * @return
  */
 public List buscaWhere(Class c, String where, String param, Object o) {
  String nome = c.getName();
  int indexPonto = nome.lastIndexOf('.');
  if (indexPonto >= 0) {
   nome = nome.substring(indexPonto + 1);
  }
  Query query = this.manager.createQuery("select c from " + nome + " c where " + where);
  query.setParameter(param, o);

  return query.getResultList();
 }

 /**
  *
  * @param c
  * @param where
  * @param param
  * @param o
  * @param order
  * @return
  */
 public List buscaWhere(Class c, String where, String param, Object o, String order) {
  String nome = c.getName();
  int indexPonto = nome.lastIndexOf('.');
  if (indexPonto >= 0) {
   nome = nome.substring(indexPonto + 1);
  }
  Query query = this.manager.createQuery("select c from " + nome + " c where " + where + " " + order);
  query.setParameter(param, o);

  return query.getResultList();
 }

 /**
  * executa uma pesquisa qualquer
  * @param pesquisa comando JPQL para ser executado
  * @return lista com os dados retornados
  */
 public List buscaGenerica(String pesquisa) {
  Query q = manager.createQuery(pesquisa);
  return q.getResultList();
 }

 /**
  * busca para trabalhar com namedqueries
  * @param pesquisa é a sequencia JPQL para execução
  * @param keys é um vetor com os nomes dos parâmetros
  * @param values é um vetor com os valores que devem ser atribuídos aos parâmetros
  * @return uma lista com objetos do BD
  */
 public List buscaGenericaNamed(String pesquisa, Object[] keys, Object[] values) {
  Query q = manager.createNamedQuery(pesquisa);
  for (int i = 0; i < keys.length; i++) {
   q.setParameter("" + keys[i], values[i]);
  }
  return q.getResultList();
 }

 /**
  * efetua uma pesquisa no BD
  * @param pesquisa é a sequencia JPQL para execução
  * @param keys é um vetor com os nomes dos parâmetros
  * @param values é um vetor com os valores que devem ser atribuídos aos parâmetros
  * @return uma lista com objetos do BD
  */
 public List buscaGenericaHQL(String pesquisa, Object[] keys, Object[] values) {
  List l = null;
  Query q = manager.createQuery(pesquisa);
  try {
   for (int i = 0; i < keys.length; i++) {
    if (values[i] != null) {
     q.setParameter("" + keys[i], values[i]);
    }
   }
   l = q.getResultList();
  } catch (Exception ex) {
   System.out.println("" + ex.getMessage());
  }
  return l;
 }
 
 public Object objetoGenericoHQL(String pesquisa, Object[] keys, Object[] values) {
  Object l = null;
  Query q = manager.createQuery(pesquisa);
  try {
   for (int i = 0; i < keys.length; i++) {
    if (values[i] != null) {
     q.setParameter("" + keys[i], values[i]);
    }
   }
   l = q.getSingleResult();
  } catch (Exception ex) {
   System.out.println("" + ex.getMessage());
  }
  return l;
 }
}
