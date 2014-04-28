$(document).ready(function(){
	$.getJSON("../json/get_rhthem.json",{"song_name":getParameterByName("song_name")},function(data){
		var $song = $("#song"); 
		var $rhythm = $("#rhythm");
		var strrhythm = "";
		var strHtml = ""; 
		var vsstrHtml = ""; 
		$song.empty(); 
		$rhythm.empty();

		strHtml = "<label>对战曲目："+data.song_name+"</label>";	
		$song.html(strHtml);

		var insertText =  "<audio id='gamesong' src='"+ data.url+"'></audio>"; 
		document.getElementById("song").innerHTML = document.getElementById("song").innerHTML+insertText;
		document.getElementById("gamesong").addEventListener("ended",function() {
			miss = data.rhythm.length - perfect -cool;
	        document.location.href = "./result.html?perfect="+perfect+"&cool="+cool+"&miss="+miss+"&combo="+combo;
	    });
	    

		for(var i in data.rhythm){
			strrhythm = "<img class='rhythm' style='width:71px;top:46px;z-index:4;'  id='rhythm"+ i +"' src='../images/"+ data.rhythm[i].color +".png'>";
			document.getElementById("rhythm").innerHTML = document.getElementById("rhythm").innerHTML+strrhythm; 
		}		
		
		//节奏点自适应屏幕缩放
		for(k in data.rhythm){			
			var times = document.body.clientWidth/1377*0.83;
			var wid = parseInt(document.getElementById("rhythm" + k).style.width)*times;
			$("#" +"rhythm" + k).css("width",wid);

			var change = document.body.clientWidth/1377*0.83*165/2;
			var newtop = parseInt(document.getElementById("rhythm" + k).style.top) + change - wid/2;
			$("#" +"rhythm" + k).css("top",newtop);
		}
	}); 	
})