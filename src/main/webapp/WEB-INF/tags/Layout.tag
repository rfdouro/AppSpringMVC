<%-- 
    Document   : Layout
    Created on : 4 de out de 2018, 09:48:08
    Author     : romulo.douro
--%>

<%@tag description="Template customizado" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<c:set var="host" value="https://${pageContext.servletContext.virtualServerName}:${pageContext.request.localPort}${pageContext.servletContext.contextPath}" scope="session" />

<!DOCTYPE html>
<html>
 <head>
  <link rel="icon" type="image/png" href="" />
  <meta charset="utf-8">
  <meta name="format-detection" content="telephone=no">
  <meta http-equiv="x-rim-auto-match" content="none">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>${TITULOPAGINA}</title>

  <!-- estilo normal -->
  <!--link href="${cp}/resources/vendor/twitter-bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/-->
  <!-- outros estilos -->
  <!--link href="https://bootswatch.com/3/spacelab/bootstrap.min.css" rel="stylesheet" type="text/css"/-->
  <link href="https://bootswatch.com/3/cerulean/bootstrap.min.css" rel="stylesheet" type="text/css"/>
  <!--link href="https://bootswatch.com/3/journal/bootstrap.min.css" rel="stylesheet" type="text/css"/-->
  <!--link href="https://bootswatch.com/3/slate/bootstrap.min.css" rel="stylesheet" type="text/css"/-->
  <!--link href="https://bootswatch.com/3/simplex/bootstrap.min.css" rel="stylesheet" type="text/css"/-->
  
  
  <link href="https://fonts.googleapis.com/css?family=Exo+2" rel="stylesheet">
  <link href="${cp}/resources/vendor/bootstrap-submenu/css/bootstrap-submenu.min.css" rel="stylesheet" type="text/css"/>
  <link href="${cp}/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
  <script src="${cp}/resources/vendor/jquery/jquery.min.js" type="text/javascript"></script>
  <script src="${cp}/resources/vendor/twitter-bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

  <link href="${cp}/resources/css/estiloMenuHover.css" rel="stylesheet" type="text/css"/>
  <script src="${cp}/resources/vendor/bootstrap-submenu/js/bootstrap-submenu.min.js" type="text/javascript"></script>

  <!--Scripts adicionais-->
  <script src="${cp}/resources/vendor/bootbox.js/bootbox.min.js" type="text/javascript"></script>

  <link href="${cp}/resources/css/telaSplash.css" rel="stylesheet" type="text/css"/>
  <script src="${cp}/resources/js/telaSplash.js" type="text/javascript"></script>
  <script src="${cp}/resources/js/navegacao.js" type="text/javascript"></script>


 </head>
 <body onload="escondeSplash()">

  <div id="telaSplash">
   <div id="divInternoSplash">
    <img src="${cp}/resources/images/ajax-loader.gif" alt="Carregando Página" style="position:absolute; top:50%; left:50%; margin-left: -110px; margin-top:-10px" />
   </div>
  </div>


    <%@include file="comuns/navbar.jsp" %>



  <div class="container">
   <div id="divConteudo">
    <div class="jumbotron">
     <jsp:doBody/>
    </div>
   </div>
  </div>


  <script>
   customizaNavegacaoJQuery();

   $(document).ready(function () {
    $('[data-submenu]').submenupicker();
    
    $.ajaxSetup({cache: false});
    
    $(document).ajaxStart(function () {
     mostraSplash();
    });
    
    $(document).ajaxComplete(function () {
     escondeSplash();
    });
   });
  </script>

 </body>
</html>