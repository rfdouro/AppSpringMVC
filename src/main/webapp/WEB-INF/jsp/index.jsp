<%-- 
    Document   : index
    Created on : 3 de out de 2018, 14:03:48
    Author     : romulo.douro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="rfd" uri="/WEB-INF/tlds/RFDOURO" %>
<!DOCTYPE html>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>${mensagem}</title>
 </head>
 <body>
  <h1>${mensagem}</h1>
  <rfd:Saudacao nome="${USULOGADO}"/>
  
 </body>
</html>
