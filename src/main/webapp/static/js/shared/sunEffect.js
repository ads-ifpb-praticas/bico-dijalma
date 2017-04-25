var sunny = true;

$(document).ready(function(){

  window.setInterval(function(){
    changeStar();
  }, 4000);
});


function changeStar(){
  var star = $('#star');
  if (sunny){
      star.css({
          transform: 'rotate(180deg)',
          transitionDuration: '4s'
      });
  }else {
      star.css({
          transform: 'rotate(0deg)',
          transitionDuration: '4s'
      });
  }
  sunny = !sunny;
  // star.addClass('animated flash');
  // setTimeout(function () {
  //   star.removeClass('animated flash');
  // }, 1000);
}
