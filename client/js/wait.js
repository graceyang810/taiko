$(document).ready(function(){
	$("#start").click(function(event) {		
	      // event.preventDefault();
	      $.getJSON("../json/get_quickstart.json",{'id':$.cookie('player_id')},function(data){
	      		if(data.feedback == true){
	      			document.location.href = "./game.html?id=" + data.id + "&name=" + data.name + "&avator=" + data.avator + "&level=" + data.level +"&song_name=" + data.song_name;
	      		}
	      		if(data.feedback == false){
	      			$("#modal").modal({
				        escapeClose: false,
				        clickClose: false,
				        showClose: false
				      });
	      		}
	      });
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