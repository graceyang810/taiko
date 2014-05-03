$(document).ready(function(){
	$("#avator1").attr("src",$.cookie('player_avator'));
	$("#name1").html($.cookie('player_name'));
	$("#level_1").html($.cookie('player_level'));
})