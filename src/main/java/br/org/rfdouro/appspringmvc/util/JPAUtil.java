/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.rfdouro.appspringmvc.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author romulo.douro
 */
public class JPAUtil {

 private static EntityManagerFactory emFactory;

 static {
  try {
   emFactory = Persistence.createEntityManagerFactory("basededados-h2-pu");
  } catch (Throwable ex) {
   System.err.println("Impossível criar EntityManagerFactory.");
   throw new ExceptionInInitializerError(ex);
  }
 }

 public static EntityManager createEntityManager() {
  return emFactory.createEntityManager();
 }

 public static void close() {
  emFactory.close();
 }

 public static EntityManager createEntityManager(String persistenceUnit) {
  try {
   if (emFactory == null || (emFactory != null && !emFactory.isOpen())) {
    emFactory = Persistence.createEntityManagerFactory(persistenceUnit);
   }
  } catch (Throwable ex) {
   System.err.println("Impossível criar EntityManagerFactory.");
   throw new ExceptionInInitializerError(ex);
  }
  return emFactory.createEntityManager();
 }

}
