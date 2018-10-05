<%-- 
    Document   : index
    Created on : 4 de out de 2018, 10:30:37
    Author     : romulo.douro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<script src="${cp}/resources/vendor/vue/vue.min.js" type="text/javascript"></script>

<div id="app">
 <h1>Lista de Pessoas</h1>
 <form v-on:submit.prevent="salva()">
  <fieldset>
   <legend>Cadastro</legend>
   <div class="row">
    <div class="col-sm-8">
     <input class="form-control" type="text" name="nome" v-model.lazy="pessoa.nome"/> 
    </div>
    <div class="col-sm-4">
     <input class="btn btn-default" type="submit" value="Salvar"/> 
     <button class="btn btn-default" v-on:click.prevent="getLista">Atualiza</button>
    </div>
   </div>
  </fieldset>
 </form>

 <ol>
  <li v-for="p in listaPessoas">{{p.nome}}</li>
 </ol>
</div>

<script>
 var v = new Vue({
  el: "#app",
  data: {
   pessoa: {
    nome: null
   },
   listaPessoas: []
  },
  methods: {
   salva: function () {
    mostraSplash();
    $.post("ws/pessoa", this.pessoa, function (data) {
     bootbox.alert(data, function () {
      v.getLista();
     });
    });
    return false;
   },
   getLista: function () {
    $.get("ws/pessoa", null, function (data) {
     v.listaPessoas = data;
     escondeSplash();
    });
    return false;
   }
  }
 });

 v.getLista();
</script>

