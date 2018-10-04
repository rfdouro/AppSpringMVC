/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.rfdouro.appspringmvc.tags;

/**
 *
 * @author romulo.douro
 */
public class StringsUtil {

 public static String encurtaString(String p, Integer t) {
  int len = p.length();
  String ret = "";
  if (p.length() <= (t - 3)) {
   ret = p;
  } else {
   ret = p.substring(0, t) + "..." + p.substring(len - 4, len - 0);
  }
  return ret;
 }
}
