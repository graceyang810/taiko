$(document).ready(function() {
	var pepe = $.fn.fullpage({
		verticalCentered: false,
		navigation: true,
		navigationPosition: 'right'
	});

	$("#loginsubmitbtn").click(function(){
		console.log("click");
		$.getJSON("../json/get_player.json",{'id':checkid,'code':con_checkcode},function(data){
			$("#name").html(data.name);
			$.cookie('player_id',data.id);
			$.cookie('player_name',data.name);
			$.cookie('player_sex',data.sex);
			$.cookie('player_avator',data.avator);
			$.cookie('player_level',data.level);			
		});
	});
		
	if($.cookie('the_sound')){
				document.getElementById('buttonplayer').volume = $.cookie('the_sound')/100;
	}else{
		document.getElementById('buttonplayer').volume = 0.9
	}	

	$("#goback").hover(
		function(){
		$("#pic3").attr("src","../images/btn_back_hover.png");
		document.getElementById('buttonplayer').play(); 
		},
		function(){
		$("#pic3").attr("src","../images/btn_back.png");
		})

	$("#id_switchrepaint2").hover(
		function(){
		$("#id_switchrepaint2").attr("style","background:url(../images/repaint_hover.png) no-repeat;background-size:60%;position:absolute;bottom:-30%;left:26%;width:80%;height:28%;border:0;outline:none;");
		document.getElementById('buttonplayer').play(); 
		},
		function(){
		$("#id_switchrepaint2").attr("style","background:url(../images/repaint.png) no-repeat;background-size:60%;position:absolute;bottom:-30%;left:26%;width:80%;height:28%;border:0;outline:none;");
		})
	$("#id_switchrepaint").hover(
		function(){
		$("#id_switchrepaint").attr("style","background:url(../images/repaint_hover.png) no-repeat;background-size:60%;position:absolute;bottom:-36%;left:26%;width:80%;height:28%;border:0;outline:none;");
		document.getElementById('buttonplayer').play(); 
		},
		function(){
		$("#id_switchrepaint").attr("style","background:url(../images/repaint.png) no-repeat;background-size:60%;position:absolute;bottom:-36%;left:26%;width:80%;height:28%;border:0;outline:none;");
		})
	$("#loginsubmitbtn").hover(
		function(){
		$("#loginsubmitbtn").attr("style","background:url(../images/submit_hover.png) no-repeat;position:absolute;top:50%;left:40%;width:20%;height:16%;border:0;outline:none;");
		document.getElementById('buttonplayer').play(); 
		},
		function(){
		$("#loginsubmitbtn").attr("style","background:url(../images/submit.png) no-repeat;position:absolute;top:50%;left:40%;width:20%;height:16%;border:0;outline:none;");
		})
	
	$("#playbtn").hover(
		function(){
		$("#pic1").attr("src","../images/play_hover.png");
		document.getElementById('buttonplayer').play(); 
		},
		function(){
		$("#pic1").attr("src","../images/play.png");
		})
});

