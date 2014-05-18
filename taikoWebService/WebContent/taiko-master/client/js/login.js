$(document).ready(function() {
	var pepe = $.fn.fullpage({
		verticalCentered: false,
		navigation: true,
		navigationPosition: 'right'
	});

	$("#loginsubmitbtn").click(function(){
		console.log("click");
		login();
	});

	function login(){
		$.getJSON("http://59.77.6.18:4081/taikoWebService/account/login",{'id':checkid,'code':con_checkcode},function(data){
			if(data[0].feedback == true){
				$("#name").html(data[1].name);
				$.cookie('player_id',data[1].id);
				$.cookie('player_name',data[1].name);
				$.cookie('player_sex',data[1].sex);
				$.cookie('player_avatar',data[1].avatar);
				$.cookie('player_level',data[1].level);
			}		
			if(data[0].feedback == false){
				$("#modal1").modal({	      				
			        escapeClose: true,
			        clickClose: true,
			        showClose: true
			      });
				setTimeout("$( '#modal1 a.close-modal').trigger( 'click')", 3000);
			}	
		});
	}
		
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

