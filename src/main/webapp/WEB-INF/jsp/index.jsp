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

 <h1>Spring MVC 5 + WebSocket + Hello World example</h1>
 <hr />
 <label>Message</label>
 <br>
 <textarea rows="8" cols="50" id="clientMsg"></textarea>
 <br>
 <button onclick="send()">Send</button>
 <br>
 <label>Response from Server</label>
 <br>
 <textarea rows="8" cols="50" id="serverMsg" readonly="readonly"></textarea>


 <script type="text/javascript">
  //Open the web socket connection to the server
  var socketConn = new WebSocket('ws://localhost:8080/AppSpringMVC/socketHandler');

  //Send Message
  function send() {
   var clientMsg = document.getElementById('clientMsg');
   if (clientMsg.value) {
    socketConn.send(clientMsg.value);
    clientMsg.value = '';
   }
  }

  // Recive Message
  socketConn.onmessage = function (event) {
   var serverMsg = document.getElementById('serverMsg');
   serverMsg.value = event.data;
  }
 </script>

</tmp:Layout>
