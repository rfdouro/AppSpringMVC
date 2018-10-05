/**
 * função adaptada para carregar uma página num div 
 * usa jquery load
 * usa telaSplash.js
 * @param {type} div
 * @param {type} link
 * @returns {Boolean}
 */
function loadInDiv(div, link) {
 mostraSplash();
 $("#" + div).load(link, function () {
  escondeSplash();
 });
 return false;
}

/**
 * faz a customização dos tags <a>
 * configurando o onclick
 * @param {type} element
 * @returns {undefined}
 */
function configuraNavegacao(element) {
 //tem href e data-div
 if (element.getAttribute("data-div") && element.getAttribute("href")) {
  //pega o valor do atributo do div
  var divSaida = element.getAttribute("data-div");
  var link = element.getAttribute("href");
  if (link != "#!") {
   element.setAttribute("href", "#!");
   element.setAttribute("onclick", "loadInDiv('" + divSaida + "','" + link + "')");
  }
 }
}

/**
 * procura os tags <a> e executa a customização
 * @param {type} tagName
 * @param {type} fn
 * @returns {undefined}
 */
function customizaNavegacao() {
 //procura todas as ocorrencias de tags no documento
 var tagInstances = document.getElementsByTagName("a");
 //for each occurrence run the associated function
 for (var i = 0; i < tagInstances.length; i++) {
  configuraNavegacao(tagInstances[i]);
 }
}

/**
 * função que customiza a navegação com carregamento em div
 * usa jquery
 * @returns {undefined}
 */
function customizaNavegacaoJQuery() {
 $(document).find("a").each(function () {
  var d = null;
  var h = $(this).attr("href");
  try {
   d = $(this).attr("data-div");
  } catch (ex) {
   console.log(ex);
  }
  if (d != null && h != "#!" && h != "#") {
   $(this).attr("href", "#!");
   $(this).on("click", function (evt) {
    //efetua load no div
    mostraSplash();
    $("#" + d).load(h, function () {
     escondeSplash();
    });
    return false;
   });
  }
 });
}