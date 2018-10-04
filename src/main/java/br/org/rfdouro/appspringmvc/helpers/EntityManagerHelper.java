package br.org.rfdouro.appspringmvc.helpers;

import java.io.InputStream;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;

/**
 *
 * @author romulo.douro verify
 * https://www.javatips.net/blog/c3p0-connection-pooling-example
 * https://www.mkyong.com/hibernate/how-to-configure-the-c3p0-connection-pool-in-hibernate/
 */
public class EntityManagerHelper {

 public static final String caminhoProperties = "/WEB-INF/config.properties";

 public static ServletContext sc = null;

 private static String pu = "basededados-h2-pu";
 private static EntityManagerFactory emf;

 public static void inicializaDados(ServletContext context) throws Exception {

  if (emf != null) {
   return;
  }

  try {
   InputStream is = context.getResourceAsStream(caminhoProperties);
   Properties prop = new Properties();
   prop.load(is);
   pu = prop.getProperty("pu");
  } catch (Exception ex) {
   throw ex;
  }
  if (pu != null) {
   emf = Persistence.createEntityManagerFactory(pu);
  }
 }

 public static EntityManager getEntityManager(ServletContext sc) {
  try {
   if (emf == null || (emf != null && !emf.isOpen())) {
    inicializaDados(sc);
   }
   EntityManager em = emf.createEntityManager();

   System.err.println("EM [criada] = " + em.hashCode());

   return em;
  } catch (Exception ex) {
   System.err.println("ERRO criando EM " + ex.getMessage());
  }
  return null;
 }

 public static void closeEntityManager(EntityManager em) {
  if (em != null && em.isOpen()) {
   em.close();
   System.err.println("EM [fechada] = " + em.hashCode());
  }
 }

 public static void closeEntityManagerFactory() {
  if (emf != null && emf.isOpen()) {
   emf.close();
  }
 }

 public static void beginTransaction(EntityManager em) {
  EntityTransaction et = em.getTransaction();
  if (et != null && !et.isActive()) {
   em.getTransaction().begin();
  }
 }

 public static void rollback(EntityManager em) {
  EntityTransaction tx = em.getTransaction();
  if (tx != null) {
   if (tx.isActive()) {
    em.getTransaction().rollback();
   }
  }
 }

 public static void commit(EntityManager em) {
  EntityTransaction et = em.getTransaction();
  boolean b1 = et != null;
  boolean b2 = (et != null) && et.isActive();
  boolean b3 = (et != null) && !et.getRollbackOnly();
  if (b1 && b2 && b3) {
   em.getTransaction().commit();
  }
 }

}
