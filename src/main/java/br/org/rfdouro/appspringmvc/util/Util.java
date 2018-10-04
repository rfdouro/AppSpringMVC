/*
 * -----------------
 * -----------------
 * -----------------
 */
package br.org.rfdouro.appspringmvc.util;

import br.org.rfdouro.appspringmvc.helpers.EntityManagerHelper;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author romulo.douro
 */
public class Util {
  
 public static final ThreadLocal<SimpleDateFormat> tsdf = new ThreadLocal<SimpleDateFormat>() {
  @Override
  protected SimpleDateFormat initialValue() {
   return new SimpleDateFormat("dd/MM/yyyy");
  }
 };

 //urlRequest = "http://xxxxxxxxx/servicos/acessoPublico/ctrlUsuario.aspx"
 //params = "acao=logar&login=" + this.usuLogado + "&senha=" + this.senhaLogado
 public static String resultToPost(String urlRequest, String params) {
  String ret = "";
  try {
   String urlParameters = params;
   byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
   int postDataLength = postData.length;
   URL url = new URL(urlRequest);
   HttpURLConnection con = (HttpURLConnection) url.openConnection();
   con.setDoOutput(true);
   con.setInstanceFollowRedirects(false);
   con.setRequestMethod("POST");
   con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
   con.setRequestProperty("charset", "utf-8");
   con.setRequestProperty("Content-Length", Integer.toString(postDataLength));
   try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
    wr.write(postData);
   }
   con.connect();
   InputStreamReader reader = new InputStreamReader(con.getInputStream());
   BufferedReader br = new BufferedReader(reader);

   String linha = "";
   while ((linha = br.readLine()) != null) {
    ret += linha;
   }
   br.close();
  } catch (Exception ex) {

  }
  return ret;
 }

 //urlRequest = "http://xxxxxxxx/servicos/acessoPublico/ctrlUsuario.aspx"
 //params = "acao=logar&login=" + this.usuLogado + "&senha=" + this.senhaLogado
 public static String resultToGet(String urlRequest, String params) {
  String ret = "";
  try {
   URL url = new URL(urlRequest + "?" + params);
   InputStreamReader reader = new InputStreamReader(url.openStream());
   BufferedReader br = new BufferedReader(reader);

   String linha = "";
   while ((linha = br.readLine()) != null) {
    ret += linha;
   }
   br.close();
  } catch (Exception ex) {

  }
  return ret;
 }

 public static String getUsuLogado(HttpServletRequest req) {
  //usando o Waffle https://github.com/Waffle/waffle
  //baseado no exemplo waffle-filter-1.7.4
  if (req.getUserPrincipal() != null) {
   String usu = req.getUserPrincipal().getName().replace('\\', '@');
   String[] dominioUsu = usu.split("@");
   return dominioUsu[1];
  }
  return null;
 }

 public static boolean verificaAcesso(HttpServletRequest req, String programa) {
  Integer id = 0;
  try {
   id = Integer.parseInt(Util.getValorPropriedade(req.getServletContext(), programa));
  } catch (Exception ex) {

  }

  Boolean config = false;
  try {
   config = Boolean.parseBoolean(Util.getValorPropriedade(req.getServletContext(), "config"));
  } catch (Exception ex) {

  }

  //se estiver configurando então passa 
  //boolean permite = (config || verificaAcesso(u, p));
  boolean permite = (config || true);
  return permite;
 }

 /*public static boolean verificaAcesso(Usuarios u, Programas p) {
  if (u != null && u.getAcessos() != null && p != null) {
   for (Acesso a : u.getAcessos()) {
    if (a.getPrograma().getIdPrograma() == p.getIdPrograma()) {
     return true;
    }
   }
  }
  return false;
 }*/
 public static String getValorPropriedade(Properties prop, String key) {
  String ret = null;
  try {
   ret = prop.getProperty(key);
  } catch (Exception ex) {

  }
  return ret;
 }

 public static String getValorPropriedade(ServletContext context, String camihoProperties, String key) {
  String ret = null;
  try {
   InputStream is = context.getResourceAsStream(camihoProperties);
   Properties prop = new Properties();
   prop.load(is);
   ret = getValorPropriedade(prop, key);
  } catch (Exception ex) {

  }
  return ret;
 }

 public static String getValorPropriedade(ServletContext context, String key) {
  return getValorPropriedade(context, EntityManagerHelper.caminhoProperties, key);
 }

 public static String getMsgErro(Exception ex) {
  String msgErro = "Ocorreu um erro ";

  Throwable t = ex;
  msgErro += "\n" + t.getMessage();
  while (t.getCause() != null) {
   t = t.getCause();
   msgErro += "\n" + t.getMessage();
  }

  return msgErro;
 }
 
 public static Cookie getCookie(HttpServletRequest request, String cookieName) {
  Cookie[] cookies = request.getCookies();
  if (cookies != null) {
   for (Cookie cookie : cookies) {
    if (cookie.getName().equals(cookieName)) {
     return cookie;
    }
   }
  }
  return null;
 }
 
 public static String encode(String v){
  //byte[] b = org.springframework.security.crypto.codec.Base64.encode(v.getBytes());
  /*char[] b = org.springframework.security.crypto.codec.Hex.encode(v.getBytes());
  return new String(b);*/
  BasicTextEncryptor encriptador = new BasicTextEncryptor();
  encriptador.setPassword("1234567980");
  return encriptador.encrypt(v);
 }
 
 public static String decode(String v){
  //byte[] b = org.springframework.security.crypto.codec.Base64.decode(v.getBytes());
  /*byte[]  b = org.springframework.security.crypto.codec.Hex.decode(v);
  return new String(b);*/
  BasicTextEncryptor encriptador = new BasicTextEncryptor();
  encriptador.setPassword("1234567980");
  return encriptador.decrypt(v);
 }
}
