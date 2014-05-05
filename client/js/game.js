$(document).ready(function(){
	$(document).on('touchmove', function(e) {
	    e.preventDefault();
	});
	$(document).on('touchstart', function(e) {
		if ($(event.target).attr("id") == "_drum0") {
			// alert("left");
			op_left();
		} else if ($(event.target).attr("id") == "_drum1") {
			// alert("right");
			op_right();
		}
	    // if (e.target.nodeName !== 'INPUT') {
	    //     e.preventDefault();
	    // }
	});
	window.setTimeout(changesize(),0);
	// changesize();
	$('#ready').click(function(){
		readygo();
		// $("#ready").css({"-webkit-animation":"1s disappear linear","-webkit-transform":"scale(2,2)"});
		// setTimeout("$('#ready').fadeOut()",1000);
		// $("#ready").css("opacity",0);
		$('#ready').fadeOut(500);
		$("#_drum0").addClass("jump");
		$("#_drum1").addClass("jump");
	})


	function readygo(){
		document.getElementById("gamesong").play(); 
		$.getJSON(getParameterByName("song_rhythem"),function(data){
		$(".move").css("-webkit-animation",data.length+"s move infinite linear");
		// 节奏点平移动画
		for(j in data.rhythm) {
			beat("#rhythm" + j, data.rhythm[j].time);
		}
		function beat(id, time) {
			setTimeout(function() {
				$(id).addClass("beat");
			}, time);
		}
		});		
	}


	var tempscore = 0;
	var combo = 0;
	var tempcombo = 0;
	var miss = 0;
	var cool = 0;
	var perfect = 0;
	$(document).keydown(function(event){ 		
		if(event.keyCode == 70){
			op_left();
		}                    
		if(event.keyCode == 74){
			op_right();
		}
	})  

	function op_left(){

			$("#_drum0").animate({
				bottom:'-70px',
			    width:'32%'
			},20, function() {
				$("#_drum0").animate({
					bottom:'-80px',
				    width:'30%'
				});
			});
			$.getJSON(getParameterByName("song_rhythem"),function(data){			
			for(var n in data.rhythm){
				if(data.rhythm[n].color == "huang"){
					// left_perfect
					if(parseInt($("#"+"rhythm"+n).css("left"))/document.body.clientWidth > 0.01 && parseInt($("#"+"rhythm"+n).css("left"))/document.body.clientWidth < 0.04){
						// console.log(n+":"+ parseInt($("#"+"rhythm"+n).css("left"))/document.body.clientWidth*100+"%");
						// console.log("left_perfect");
						$("#"+"rhythm"+n).css("visibility","hidden");
						tempscore += 5;						
						$("#currentscore1").html(tempscore);
						perfect++;
						// console.log("perfect:"+perfect);
						tempcombo++;
						n++;	
						// 动画
						strlevel = "<img id='perfect"+n +"' style='position:absolute;top:40px;left:1.5%;z-index:4;width:10%;' src='../images/perfect.png'>";
						$("#dislevel").empty();
						document.getElementById("dislevel").innerHTML = document.getElementById("dislevel").innerHTML+strlevel; 
						$( "#" +"perfect" + n).animate({top:'-=60px',opacity:'0',width:'150px'},"slow", function() {
						});
						continue;
						}
					// left_cool
					if(parseInt($("#"+"rhythm"+n).css("left"))/document.body.clientWidth > 0.04 && parseInt($("#"+"rhythm"+n).css("left"))/document.body.clientWidth < 0.07){
						// console.log(n+":"+ parseInt($("#"+"rhythm"+n).css("left"))/document.body.clientWidth*100+"%");
						// console.log("left_cool");
						$("#"+"rhythm"+n).css("visibility","hidden");
						tempscore += 3;
						$("#currentscore1").html(tempscore);						
						cool++;
						// console.log("cool:"+cool);
						tempcombo++;
						n++;		
						// 动画
						strlevel = "<img id='cool"+n +"' style='position:absolute;top:40px;left:1.5%;z-index:4;width:10%;' src='../images/cool.png'>";
						$("#dislevel").empty();
						document.getElementById("dislevel").innerHTML = document.getElementById("dislevel").innerHTML+strlevel; 
						$( "#" +"cool" + n).animate({top:'-=60px',opacity:'0',width:'150px'},"slow",function() {
						});
						continue;
						}	
					// left_miss
					if(parseInt($("#"+"rhythm"+n).css("left"))/document.body.clientWidth > 0.07 && parseInt($("#"+"rhythm"+n).css("left"))/document.body.clientWidth < 0.15||parseInt($("#"+"rhythm"+n).css("left"))/document.body.clientWidth < 0.01&&parseInt($("#"+"rhythm"+n).css("left"))/document.body.clientWidth > -0.01){
						// console.log(n+":"+ parseInt($("#"+"rhythm"+n).css("left"))/document.body.clientWidth*100+"%");
						// console.log("left_miss");					
						miss++;
						// console.log("miss:"+miss);
						if(tempcombo > combo){
							combo = tempcombo;
							tempcombo = 0;
							// console.log(combo);
							}
						else{
							tempcombo = 0;
						}
						n++;	
						// 动画
						strlevel = "<img id='miss"+n +"' style='position:absolute;top:40px;left:1.5%;z-index:4;width:10%;' src='../images/miss.png'>";
						$("#dislevel").empty();
						document.getElementById("dislevel").innerHTML = document.getElementById("dislevel").innerHTML+strlevel; 
						$( "#" +"miss" + n).animate({top:'-=60px',opacity:'0',width:'150px'},"slow",function() {
						});
						continue;
						}
					else{
						continue;
					}
				}
			}
	});
	
	}
	function op_right(){
			$("#_drum1").animate({
				bottom:'-70px',
			    width:'32%'
			},20, function() {
				$("#_drum1").animate({
					bottom:'-80px',
				    width:'30%'
				});
			});
			$.getJSON(getParameterByName("song_rhythem"),function(data){			
			for(var m in data.rhythm){
				if(data.rhythm[m].color == "hong"){
					// right_perfect
					if(parseInt($("#"+"rhythm"+m).css("left"))/document.body.clientWidth > 0.01 && parseInt($("#"+"rhythm"+m).css("left"))/document.body.clientWidth < 0.04){
						// console.log(m+":"+ parseInt($("#"+"rhythm"+m).css("left"))/document.body.clientWidth*100+"%");
						// console.log("right_perfect");
						$("#"+"rhythm"+m).remove();
						tempscore += 5;			
						$("#currentscore1").html(tempscore);		
						perfect++;
						// console.log("perfect:"+perfect);
						tempcombo++;
						m++;
						// 动画
						strlevel = "<img id='perfect"+m +"' style='position:absolute;top:40px;left:1.5%;z-index:4;width:10%;' src='../images/perfect.png'>";
						$("#dislevel").empty();
						document.getElementById("dislevel").innerHTML = document.getElementById("dislevel").innerHTML+strlevel; 
						$( "#" +"perfect" + m).animate({top:'-=60px',opacity:'0',width:'150px'},"slow",function() {
						});
						continue;
						}
					// right_cool
					if(parseInt($("#"+"rhythm"+m).css("left"))/document.body.clientWidth > 0.04 && parseInt($("#"+"rhythm"+m).css("left"))/document.body.clientWidth < 0.07){
						// console.log(m+":"+ parseInt($("#"+"rhythm"+m).css("left"))/document.body.clientWidth*100+"%");
						// console.log("right_cool");
						$("#"+"rhythm"+m).remove();
						tempscore += 3;
						$("#currentscore1").html(tempscore);					
						cool++;
						// console.log("cool:"+cool);
						tempcombo++;
						m++;	
						// 动画
						strlevel = "<img id='cool"+m +"' style='position:absolute;top:40px;left:1.5%;z-index:4;width:10%;' src='../images/cool.png'>";
						$("#dislevel").empty();
						document.getElementById("dislevel").innerHTML = document.getElementById("dislevel").innerHTML+strlevel; 
						$( "#" +"cool" + m).animate({top:'-=60px',opacity:'0',width:'150px'},"slow",function() {
						});
						continue;
						}	
					// right_miss
					if(parseInt($("#"+"rhythm"+m).css("left"))/document.body.clientWidth > 0.07 && parseInt($("#"+"rhythm"+m).css("left"))/document.body.clientWidth < 0.15||parseInt($("#"+"rhythm"+m).css("left"))/document.body.clientWidth < 0.01&&parseInt($("#"+"rhythm"+m).css("left"))/document.body.clientWidth > -0.01){
						// console.log(m+":"+ parseInt($("#"+"rhythm"+m).css("left"))/document.body.clientWidth*100+"%");
						// console.log("right_miss");					
						miss++;
						// console.log("miss:"+miss);						
						if(tempcombo > combo){
							combo = tempcombo;
							tempcombo = 0;
							// console.log("combo:"+combo);
							}
						else{
							tempcombo = 0;
						}
						m++;	
						// 动画
						strlevel = "<img id='miss"+m +"' style='position:absolute;top:40px;left:1.5%;z-index:4;width:10%;' src='../images/miss.png'>";
						$("#dislevel").empty();
						document.getElementById("dislevel").innerHTML = document.getElementById("dislevel").innerHTML+strlevel; 
						$( "#" +"miss" + m).animate({top:'-=60px',opacity:'0',width:'150px'},"slow",function() {
						});
						continue;
						}					
					else{
						continue;
					}
				}
			}
			});      
	}


	function changesize(){
		var newleft = document.body.clientWidth - parseInt($("#invent").css("width"))*13/18;
		// $("html").height($(window).height());
		// $("body").height(document.documentElement.clientHeight);
		$(".playername").css("left",newleft);
		$(".score").css("left",newleft);

		var fontsize = 18 * parseInt($("#invent").css("width"))/300;
		$(".playername").css("font-size",fontsize+"pt");
		$(".score").css("font-size",fontsize+"pt");

		var newtop1 = parseInt($("#invent").css("width"))/442*312*1/4;
		var _newtop1 = parseInt($("#invent").css("width"))/442*312*2/5;
		$("#playername1").css("top",newtop1);
		$("#score1").css("top",_newtop1);

		var newtop2 = parseInt($("#invent").css("width"))/442*312*26/39;
		var _newtop2 = parseInt($("#invent").css("width"))/442*312*14/17;
		$("#playername2").css("top",newtop2);
		$("#score2").css("top",_newtop2);

		var imgleft = document.body.clientWidth - parseInt($("#invent").css("width"))*17/18;
		var imgtop = parseInt($("#invent").css("top")) + parseInt($("#invent").css("width"))*13/90;
		var imgsize = parseInt($("#invent").css("width"))/6;
		$("#showavatar1").css("left",imgleft);
		$("#showavatar1").css("top",imgtop);
		$("#showavatar1").css("width",imgsize);

		var imgtop2 = parseInt($("#invent").css("top")) + parseInt($("#invent").css("width"))*41/90;
		$("#showavatar2").css("left",imgleft);
		$("#showavatar2").css("top",imgtop2);
		$("#showavatar2").css("width",imgsize);

		
	}

})




function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	results = regex.exec(location.search);
	return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}