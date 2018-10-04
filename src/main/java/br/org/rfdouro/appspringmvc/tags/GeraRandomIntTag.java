/*
 * -----------------
 * -----------------
 * -----------------
 */
package br.org.rfdouro.appspringmvc.tags;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Random;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author romulo.douro
 */
public class GeraRandomIntTag extends SimpleTagSupport {
 private Integer max;

 public void setMax(Integer max) {
  this.max = max;
 }

 private StringWriter sw = new StringWriter();
 

 @Override
 public void doTag() throws JspException, IOException {
  JspWriter out = getJspContext().getOut();
  if(this.max!=null)
   out.println((new Random()).nextInt(this.max));
  else
   out.println((new Random()).nextInt());
 }
}
