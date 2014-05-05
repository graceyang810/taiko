$(document).ready(function(){
	$.getJSON("../json/get_result.json",{"id":$.cookie('player_id'),"anotherid":getParameterByName("anotherid"),"perfect":getParameterByName("perfect"),"cool":getParameterByName("cool"),"miss":getParameterByName("miss"),"combo":getParameterByName("combo"),"score":(getParameterByName("perfect")*5 + getParameterByName("cool")*3)},function(data){
		$("#perfect2").html(data.perfect);
		$("#cool2").html(data.cool);
		$("#miss2").html(data.miss);
		$("#combo2").html(data.combo);
		$("#score2").html(data.score);
		$("#perfect").html(getParameterByName("perfect"));
		$("#cool").html(getParameterByName("cool"));
		$("#miss").html(getParameterByName("miss"));
		$("#combo").html(getParameterByName("combo"));
		$("#score").html(getParameterByName("perfect")*5 + getParameterByName("cool")*3);

		if(parseInt($("#score").html()) > parseInt($("#score2").html())){
		$("#rating").html(1);
		$("#rating2").html(2);
		$("#stamp").animate({width:'16%',top:'25%',left:'26%',opacity:'1'},"slow");
		}
		if(parseInt($("#score").html()) < parseInt($("#score2").html())){
			$("#rating").html(2);
			$("#rating2").html(1);
			$("#stamp").animate({width:'16%',top:'25%',left:'56%',opacity:'1'},"slow");
		}
	});	
	$("#avatar1").attr("src",$.cookie('player_avatar'));
	$("#name1").html($.cookie('player_name'));
	$("#level_1").html($.cookie('player_level'));
	$("#avatar2").attr("src",getParameterByName("avatar"));
	$("#name2").html(getParameterByName("name"));
	$("#level_2").html(getParameterByName("level"));
	$("#songname").html(getParameterByName("song_name"));

	

	function getParameterByName(name) {
		name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		results = regex.exec(location.search);
		return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
})
