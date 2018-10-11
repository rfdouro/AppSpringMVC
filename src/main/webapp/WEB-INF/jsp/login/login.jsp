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
   <div class="row">
    <div class="col-lg-3 col-sm-3">
     <input placeholder="Login" name="login" type="text" class="form-control" autofocus> 
    </div>
    <div class="col-lg-3 col-sm-3">
     <input placeholder="Senha" name="senha" type="password" class="form-control" value="">  
    </div>
    <div class="col-lg-3 col-sm-3">
     <input type="submit" class="btn btn-default" value="Logar"/>
    </div>
   </div>
  </fieldset>
 </form>
</tmp:Layout>
