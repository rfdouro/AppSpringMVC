<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tmp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<tmp:Layout> 
 <h2 class="headline text-yellow"> ${httpErrorCode}</h2>

 <h3><i class="fa fa-warning text-yellow"></i>Oops! ${errorMsg}</h3>
</tmp:Layout>