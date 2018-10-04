<%-- 
    Document   : login
    Created on : 4 de out de 2018, 13:22:51
    Author     : romulo.douro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tmp" tagdir="/WEB-INF/tags/" %>
<tmp:Layout>
 <form action="${cp}/login/loga" method="POST">
  <input type="hidden" name="redir" value="${redir}"/>
  <fieldset>
   <legend>Login</legend>
   <input placeholder="Login" name="login" type="text" autofocus>
   <input placeholder="Senha" name="senha" type="password" value="">
   <input type="submit" value="Logar"/>
  </fieldset>
 </form>
</tmp:Layout>
