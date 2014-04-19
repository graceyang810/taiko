$(document).ready(function(){
	$("#start").click(function(event) {
	      event.preventDefault();
	      $("#modal").modal({
	        escapeClose: false,
	        clickClose: false,
	        showClose: false
	      });
	      // $.getJSON("../json/get_quickstart.json",function(data){
	      // 	console.log($.cookie('player_id'));
	      // });
	    });

		$("#start").hover(
			function(){
    		$("#start").attr("src","../images/start_hover.png");
    		document.getElementById('buttonplayer').play(); 
			},
			function(){
			$("#start").attr("src","../images/start.png");
			})
		$("#shake").hover(
			function(){
    		$("#pic2").attr("src","../images/shake_hover.png");
    		document.getElementById('buttonplayer').play(); 
			},
			function(){
			$("#pic2").attr("src","../images/shake.png");
			})
		$("#goback").hover(
			function(){
    		$("#pic3").attr("src","../images/btn_back_hover.png");
    		document.getElementById('buttonplayer').play(); 
			},
			function(){
			$("#pic3").attr("src","../images/btn_back.png");
			})
})