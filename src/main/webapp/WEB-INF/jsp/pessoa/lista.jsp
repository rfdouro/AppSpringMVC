<%-- 
    Document   : index
    Created on : 4 de out de 2018, 10:30:37
    Author     : romulo.douro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

