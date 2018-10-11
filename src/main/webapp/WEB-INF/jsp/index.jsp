<%-- 
    Document   : index
    Created on : 3 de out de 2018, 14:03:48
    Author     : romulo.douro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="rfd" uri="/WEB-INF/tlds/RFDOURO" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tmp" %>
<tmp:Layout>
 <h2>
  <rfd:Saudacao nome="${USULOGADO}"/>
 </h2>

</tmp:Layout>
