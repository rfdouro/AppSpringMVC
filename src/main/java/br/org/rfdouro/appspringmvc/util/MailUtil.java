/*
 * -----------------
 * -----------------
 * -----------------
 */
package br.org.rfdouro.appspringmvc.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author romulo.douro
 * usado para gmail
 */
public class MailUtil {

 static final String username = "";
 static final String password = "";

 static final Properties props = new Properties();

 static {
  props.put(
          "mail.smtp.auth", "true");
  props.put(
          "mail.smtp.starttls.enable", "true");
  props.put(
          "mail.smtp.host", "smtp.gmail.com");
  props.put(
          "mail.smtp.port", "587");
 }

 public static boolean sendMailTLS(String emailDe, String emailPara, 
         String assunto, String texto) throws Exception {
  boolean ret = false;

  Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
   protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(username, password);
   }
  });

  try {

   Message message = new MimeMessage(session);
   message.setFrom(new InternetAddress(emailDe));
   message.setRecipients(Message.RecipientType.TO,
           InternetAddress.parse(emailPara));
   message.setSubject(assunto);
   message.setText(texto);

   Transport.send(message);
   
   ret = true;

  } catch (MessagingException e) {
   throw e;
  }
  return ret;
 }
 
}
