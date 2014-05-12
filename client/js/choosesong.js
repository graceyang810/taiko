$(document).ready(function(){
	for(var n = 0; n < getParameterByName("songnum"); n++){
		var strsong = "<li class='songs' id='song_"+n+"' ><h2 class='songname' id="+ $.cookie('songlist'+n+'_id') +" >"+ $.cookie('songlist'+n+'_name')+"</h2><p class='songlevel' style='margin-top:20px;'>难度: Lv.<span id='songlevel_"+n+"'>" + $.cookie('songlist'+n+'_level') + "</span></p></li>";
		document.getElementById("list").innerHTML = document.getElementById("list").innerHTML+strsong;
	}
	$(".songname").on("click",function(){
		$(".songname").css("color","black");
		$(".songname").css("font-size","38px");
		$(".songname").css("text-shadow","");
		$(".songname").removeClass("selected");
		$(this).css("color","#fff");
		$(this).css("font-size","40pt");
		$(this).css("text-shadow","0 0 5px #fff, 0 0 10px #fff, 0 0 15px #fff, 0 0 20px #ff00de, 0 0 50px #ff00de");
		$(this).addClass("selected");
	})
	$("#letsgo").on("click",function(){
		
		$.getJSON("../json/get_selectmusic.json",{'id':$.cookie('player_id'),'song_id':$(".selected").attr("id")},function(data){
			document.location.href = "./game.html?id=" + getParameterByName("id") + "&name=" + getParameterByName("name") + "&avatar=" + getParameterByName("avatar") + "&level=" + getParameterByName("level") +"&song_name=" + $(".selected").html() +"&song_rhythem=" + data[1].rhythmURL;

			for(var m = 0; m < getParameterByName("songnum"); m++){
				$.cookie('songlist'+m+'_name', '', { expires: -1 });
				$.cookie('songlist'+m+'_id', '', { expires: -1 });	
				$.cookie('songlist'+m+'_level', '', { expires: -1 });
			}
		});
	})

})

	function getParameterByName(name) {
		name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		results = regex.exec(location.search);
		return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}