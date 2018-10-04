/*
 * -----------------
 * -----------------
 * -----------------
 */
package br.org.rfdouro.appspringmvc.tags;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author romulo.douro
 */
public class SaudacaoTag extends SimpleTagSupport {

 private String nome;

 public void setNome(String n) {
  this.nome = n;
 }

 StringWriter sw = new StringWriter();

 public void doTag()
         throws JspException, IOException {
  Calendar c = Calendar.getInstance();
  SimpleDateFormat formatDT = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
  Date date = c.getTime();  
  
  String s = " bom dia!";
  if(c.get(Calendar.HOUR_OF_DAY)<12)
   s = " bom dia!";
  else if(c.get(Calendar.HOUR_OF_DAY)<18)
   s = " boa tarde!";
  else 
   s = " boa noite!";

  String msg = ", "+s+"<br/><sub style=\"font-size: 0.5em;\">Acesso em " + formatDT.format(date) + "</sub>";

  if (nome != null) {
   /* Usa o valor do nome no atributo */
   JspWriter out = getJspContext().getOut();
   out.println("Olá " + nome + msg);
  } else {
   /* usa o valor do nome no corpo da declaração */
   getJspBody().invoke(sw);
   getJspContext().getOut().println("Olá " + sw.toString() + msg);
  }
 }

}
