$(document).ready(function(){
	$("#avator1").attr("src",$.cookie('player_avator'));
	$("#name1").html($.cookie('player_name'));
	$("#level_1").html($.cookie('player_level'));
	$(document).keydown(function(event){ 		
		if(event.keyCode == 32){
			console.log("shake~");
			$("#v").animate({left:'45%'});
			$("#v").animate({left:'25%'});
			$("#player1").animate({left:'25%'});
			$("#player1").animate({left:'10%'});

			$("#s").animate({left:'45%'});
			$("#s").animate({left:'65%'});
			$("#player2").animate({right:'15%'});
			$("#player2").animate({right:'0%'});
		}                    
	})  
})