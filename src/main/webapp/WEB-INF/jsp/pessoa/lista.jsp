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

 <table class="table table-striped table-responsive">
  <thead>
   <tr><th>Nome</th><th></th></tr>
  </thead>
  <tbody>
   <tr v-for="p in listaPessoas">
    <td>{{p.nome}}</td>
    <td><button class="btn btn-danger" v-on:click.prevent="exclui(p.id)"><i class="fa fa-close"></i></button></td>
   </tr>
  </tbody>
 </table>

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
     bootbox.alert({
      title: 'Mensagem',
      message: data,
      callback: function () {
       v.getLista();
      }
     });
    });
    return false;
   },
   exclui: function (idE) {
    bootbox.confirm({
     title: 'Confirmação',
     message: "Confirma a exclusão deste registro?",
     buttons: {
      confirm: {
       label: 'Sim',
       className: 'btn-success'
      },
      cancel: {
       label: 'Não',
       className: 'btn-danger'
      }
     },
     callback: function (result) {
      if (result) {
       $.ajax({
        url: "ws/pessoa/" + idE,
        data: null,
        method: 'DELETE',
        success: function (data) {
         bootbox.alert({
          title: 'Mensagem',
          message: data,
          callback: function () {
           v.getLista();
          }
         });
        }
       });
      }
     }
    });
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

