$(document).ready(function(){
	$("#start").click(function(event) {		
	      // event.preventDefault();
		quickstart();
	    });

	function quickstart(){

	      $.getJSON("../json/get_quickstart.json",{'id':$.cookie('player_id')},function(data){
	      		if(data[0].feedback == true){
	      			document.location.href = "./game.html?id=" + data[1].id + "&name=" + data[1].name + "&avatar=" + data[1].avatar + "&level=" + data[1].level +"&song_name=" + data[2].song_name +"&song_rhythem=" + data[3].rhythmURL;
	      		}
	      		if(data[0].feedback == false){
	      			$("#modal").modal({
				        escapeClose: false,
				        clickClose: false,
				        showClose: false
				      });
	      			quickstart();
	      		}
	      });
	}

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