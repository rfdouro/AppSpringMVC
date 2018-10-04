/*
 * -----------------
 * -----------------
 * -----------------
 */
package br.org.rfdouro.appspringmvc.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author romulo.douro
 */
public class UtilConsultaCorreio {

 /**
  * converte um stream para uma string legível modificando byte a byte o
  * parâmetro de entrada
  *
  * @param is
  * @return
  * @throws IOException
  */
 private static String toString(InputStream is) throws IOException {

  byte[] bytes = new byte[1024];
  ByteArrayOutputStream baos = new ByteArrayOutputStream();
  int lidos;
  while ((lidos = is.read(bytes)) > 0) {
   baos.write(bytes, 0, lidos);
  }
  return new String(baos.toByteArray());
 }

 /**
  * efetua o download das informações utilizando as ferramentas das bibliotecas
  * http components da Apache https://hc.apache.org/
  *
  * @param params
  * @return
  */
 public static String consultaAR(String numeroAR) throws Exception {

  //String urlString = "http://www2.correios.com.br/sistemas/rastreamento/ctrl/ctrlRastreamento.cfm?acao=&objetos=JT248964145BR";
  String urlString = "http://www2.correios.com.br/sistemas/rastreamento/newprint.cfm";
  String texto = "";

  //HttpClient httpclient = new DefaultHttpClient();
  CloseableHttpClient client = HttpClients.createDefault();
  try {
   HttpPost httpPost = new HttpPost(urlString);

   List<NameValuePair> params = new ArrayList<NameValuePair>();
   //params.add(new BasicNameValuePair("acao", ""));
   params.add(new BasicNameValuePair("objetos", numeroAR.toUpperCase()));
   httpPost.setEntity(new UrlEncodedFormEntity(params));

   CloseableHttpResponse response = client.execute(httpPost);

   //System.out.println("" + response.getStatusLine().getStatusCode());
   HttpEntity entity = response.getEntity();

   if (entity != null) {
    InputStream instream = entity.getContent();

    texto = toString(instream);
    //retira a tarefa de imprimir
    texto = texto.replace("self.print();", "");
    //retira o style do html e do body
    //texto = texto.replace("html,body", "suprime1");
    //retira o style inteiro
    //texto = texto.replace("<style>", "<!--style>");
    //texto = texto.replace("</style>", "</style-->");

    instream.close();
   }
  } catch (Exception ex) {
   System.out.println("ERRO na consulta do correio = " + Util.getMsgErro(ex));
  }
  client.close();
  return texto;
 }
}
