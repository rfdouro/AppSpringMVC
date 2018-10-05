function mostraSplash() {
 $('#telaSplash').attr('style', 'display: block');
}

function escondeSplash() {
 try {
  $('#telaSplash').attr('style', 'display: none');
 } catch (ex) {
  alert(ex);
 }
}

