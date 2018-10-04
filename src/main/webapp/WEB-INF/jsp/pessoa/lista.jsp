<%-- 
    Document   : index
    Created on : 4 de out de 2018, 10:30:37
    Author     : romulo.douro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>JSP Page</title>
 </head>
 <body>
  <h1>Lista de Pessoas</h1>
  <form method="post">
   <fieldset>
    <legend>Cadastro</legend>
    <input type="text" name="nome"/>
    <input type="submit" value="Salvar"/>
   </fieldset>
  </form>
  <c:forEach items="${lista}" var="p">
   <p>${p.nome}</p>
  </c:forEach>
 </body>
</html>
