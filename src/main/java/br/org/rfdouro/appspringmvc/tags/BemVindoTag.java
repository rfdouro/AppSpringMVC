/*
 * -----------------
 * -----------------
 * -----------------
 */
package br.org.rfdouro.appspringmvc.tags;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author romulo.douro
 */
public class BemVindoTag extends SimpleTagSupport {
 StringWriter sw = new StringWriter();

 @Override
 public void doTag() throws JspException, IOException {
  JspWriter out = getJspContext().getOut();
  getJspBody().invoke(sw);
  out.println("Olá "+sw.toString());
 }
}
