<%-- 
    Document   : Layout
    Created on : 4 de out de 2018, 09:48:08
    Author     : romulo.douro
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<c:set var="host" value="https://${pageContext.servletContext.virtualServerName}:${pageContext.request.localPort}${pageContext.servletContext.contextPath}" scope="session" />

<!DOCTYPE html>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>${host}</title>
  <link href="${cp}/resources/css/estilo.css" rel="stylesheet" type="text/css"/>
 </head>
 <body>
  <jsp:doBody/>
 </body>
</html>