$(document).ready(function(){
	$("#avatar1").attr("src",$.cookie('player_avatar'));
	$("#name1").html($.cookie('player_name'));
	$("#level_1").html($.cookie('player_level'));
	$(document).keydown(function(event){ 
		if(event.keyCode == 32){
			shake();	
		}			                    
	});
	var SHAKE_THRESHOLD = 800;
    var last_update = 0;
    var x = y = z = last_x = last_y = last_z = 0;

    if (window.DeviceMotionEvent) {
        window.addEventListener('devicemotion', deviceMotionHandler, false);
    } else {
        alert('本设备不支持devicemotion事件');
    }

    function deviceMotionHandler(eventData) {
        var acceleration = eventData.accelerationIncludingGravity;
        var curTime = new Date().getTime();
        if ((curTime - last_update) > 400) {
            var diffTime = curTime - last_update;
            last_update = curTime;
            x = acceleration.x;
            y = acceleration.y;
            z = acceleration.z;
            var speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

            if (speed > SHAKE_THRESHOLD) {
            	shake();
            }
            last_x = x;
            last_y = y;
            last_z = z;
        }
    }

	$("#accept").click(function(){
		$("#modal3").modal({	      				
	        escapeClose: false,
	        clickClose: false,
	        showClose: false
	      });
		waitchoosesong();
	});	 

	function waitchoosesong(){		
		$.getJSON("http://59.77.6.18:4081/taikoWebService/process/response",{'id':$.cookie('player_id'),'anotherid':$("#anotheravtor").attr("playerid"),'feedback':1},function(data){
			if(data[0].feedback == false){
				console.log("歌未选好");
				waitchoosesong();
			}
			if(data[0].feedback == true){
				document.location.href = "./game.html?id=" + $("#anotheravtor").attr("playerid") + "&name=" + $("#name_another").html() + "&avatar=" + $("#anotheravtor").attr("src") + "&level=" + $("#level_another").html() +"&song_name=" + data[1].song_name +"&song_rhythem=" + data[2].rhythmURL;
			}
		});
	}

	$("#refuse").click(function(){	
		$.getJSON("http://59.77.6.18:4081/taikoWebService/process/response",{'id':$.cookie('player_id'),'anotherid':$("#anotheravtor").attr("playerid"),'feedback':0},function(data){
			console.log("拒绝");
		});		
	})

	$("#send").click(function(){
		$("#modal1").modal({	      				
	        escapeClose: false,
	        clickClose: false,
	        showClose: false
	      });
		sendinvite();	
	});

	function sendinvite(){
		$.getJSON("http://59.77.6.18:4081/taikoWebService/process/invite",{'id':$.cookie('player_id'),'anotherid':$("#avatar2").attr("playerid")},function(data){
			if(data[0].feedback == true){
				if(data[1].resp == 1){
					console.log("同意");
					$("#modal5").modal({	      				
				        escapeClose: false,
				        clickClose: false,
				        showClose: false
				      });
					for(var num = 0; num < data[2].length; num++ ){
						$.cookie('songlist'+ num + "_id", data[2][num].song_id);
						$.cookie('songlist'+ num + "_name", data[2][num].song_name);
						$.cookie('songlist'+ num + "_level", data[2][num].song_level);
						console.log(data[2][num]);
					}
					var a = $('#level_2').html();
					var b = "Lv.";
					var sendlevel = a.replace(eval("/"+b+"/g"),"");
					console.log(sendlevel);
					setTimeout(function gotochoosesong(){
						document.location.href='./choosesong.html?id='+$('#avatar2').attr('playerid')+'&name='+$('#name2').html()+'&avatar='+$('#avatar2').attr('src')+'&level='+sendlevel +'&songnum='+ data[2].length;
					},3000);

				}
				if(data[1].resp == 0){
					$("#modal4").modal({	      				
				        escapeClose: true,
				        clickClose: true,
				        showClose: true
				      });
					setTimeout("$( '#modal4 a.close-modal').trigger( 'click')", 3000);
				}
			}
			if(data[0].feedback == false){
				console.log("未回复");
				sendinvite();
			}
		});			
	}

	function shake(){
			$("#playerlist").empty();
			$("#playerlist").css({opacity:'0'});
			$("#v").animate({left:'45%'});
			$("#v").animate({left:'25%'});
			$("#player1").animate({left:'25%'});
			$("#player1").animate({left:'10%'});

			$("#s").animate({left:'45%'});
			$("#s").animate({left:'65%'});
			$("#player2").animate({right:'15%'});
			$("#player2").animate({right:'0%'}, function() {
				$("#playerlist").animate({opacity:'1'});
			});

			$.getJSON("http://59.77.6.18:4081/taikoWebService/process/shakestart",{'id':$.cookie('player_id')},function(data){
	      		if(data[1].feedback == true){
	      			$("#modal2").modal({	      				
				        escapeClose: false,
				        clickClose: false,
				        showClose: false
				      });
	      			$("#anothername").html(data[2].name);
	      			$("#name_another").html(data[2].name);
	      			$("#level_another").html(data[2].level);
	      			$("#anotheravtor").attr("src",data[2].avatar);
	      			$("#anotheravtor").attr("playerid",data[2].id);
	      			if(data[2].sex == "male"){
	      				$("#anothersex").attr("src","../images/showmale.png");
	      			}
	      			if(data[2].sex == "female"){
	      				$("#anothersex").attr("src","../images/showfemale.png");
	      			}
	      		}
	      		if(data.feedback == false){
	      		}		      		
      			for(var n =0;  n < data[0].length;n++){
      				var strplayer = "<div id='play_"+n+"' style='width:50%;float:left;'><img class='playeravatar' playerid="+data[0][n].id+" level=showlevel_"+n+" name=showname_"+n+" id='showplayer"+ n +"' src="+ data[0][n].avatar +"><p class='playername' id='showname_"+ n +"' >"+ data[0][n].name+"</p><p class='playerlevel' id='showlevel_"+ n +"' >Lv."+ data[0][n].level+"</p></div>";
					document.getElementById("playerlist").innerHTML = document.getElementById("playerlist").innerHTML+strplayer; 
      			}
	      });
	}


	$("#playerlist").on("click", ".playeravatar", function(){
		$(".playeravatar").css("border","0px");
		$(this).css("border","3px solid #00afe8");
		$(this).css("border-radius","10px");
		$("#avatar2").attr("src",$(this).attr("src"));	
		$("#avatar2").attr("playerid",$(this).attr("playerid"));	
		$("#name2").html($("#"+$(this).attr('name')).html());		
		$("#level_2").html($("#"+$(this).attr('level')).html());
		console.log($('#level_2').html());
		$("#pic2").css("visibility","visible");
	});	
})