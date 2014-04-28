$(document).ready(function(){
	vsstrHtml = "<label style='color:white;top:10%;left:20%;position:absolute;z-index:6;font-size:25px;'>对战曲目："+ getParameterByName("song_name") +"</label>";				
	$("#vssong").html(vsstrHtml);
	$("#avator1").attr("src",$.cookie('player_avator'));
	$("#avator2").attr("src",getParameterByName("avator"));
	$("#name1").html($.cookie('player_name'));
	$("#playername1").html("name:"+ $.cookie('player_name'));
	$("#name2").html(getParameterByName("name"));
	$("#playername2").html("name:"+ getParameterByName("name"));
	$("#level_1").html($.cookie('player_level'));
	$("#l1").html($.cookie('player_level'));
	$("#level_2").html(getParameterByName("level"));
	$("#l2").html(getParameterByName("level"));
});


function getParameterByName(name) {
			name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
			var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
			results = regex.exec(location.search);
			return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
		}