$(document).ready(function(){
			$("#id").html($.cookie('player_id'));
			$("#name").html($.cookie('player_name'));
			if($.cookie('player_sex') == "famale"){
				$("#sex").html("女");
				console.log("女");
			}
			if($.cookie('player_sex') == "male"){
				$("#sex").html("男");
			}
			$("#level").html($.cookie('player_level'));
			$("#avatar").attr("src",$.cookie('player_avatar'));
			$("#exitbtn").click(function(){
				$.cookie('player_id',"");
				$.cookie('player_name',"");
				$.cookie('player_sex',"");
				$.cookie('player_avatar',"");
				$.cookie('player_level',"");		
			});
			if($.cookie('the_sound')){
					document.getElementById('buttonplayer').volume = $.cookie('the_sound')/100;
				}else{
					document.getElementById('buttonplayer').volume = 0.9
				}	
			$("#playbtn").hover(
			function(){
    		$("#pic1").attr("src","../images/play_hover.png");
    		document.getElementById('buttonplayer').play(); 
			},
			function(){
			$("#pic1").attr("src","../images/play.png");
			})

			$("#exitbtn").hover(
			function(){
    		$("#pic2").attr("src","../images/exitbtn_hover.png");
    		document.getElementById('buttonplayer').play(); 
			},
			function(){
			$("#pic2").attr("src","../images/exitbtn.png");
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