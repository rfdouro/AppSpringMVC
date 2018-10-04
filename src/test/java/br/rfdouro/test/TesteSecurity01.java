/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.rfdouro.test;

import br.org.rfdouro.appspringmvc.util.Util;

/**
 *
 * @author romulo.douro
 */
public class TesteSecurity01 {

 public static void main(String[] args) {
  String p = "cavalo";
  String pe = Util.encode(p);
  String pd = Util.decode(pe);
  System.out.println(p + " , " + pe + " , " + pd);
 }
}
