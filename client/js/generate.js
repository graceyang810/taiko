var m_name1 = ["慕容", "任", "于", "厉", "钟离", "唐", "东方", "敖", "公孙", "南宫", "邵",  "司徒",  "尉迟", "司空", "尹", "明", "西门",  "归海", "莫", "尚", "上官"];
// var m_name1 = ["赵","钱","孙","李","周","吴","郑","王","冯","陈","褚","卫","蒋","沈","韩","杨","朱","秦","尤","许","何"];
var m_name2 = ["爵", "逸", "寒", "封", "萧", "云", "燚", "轩", "海", "元", "天", "寂" ];
// var m_name2 = ["长", "栓", "大", "来", "狗", "守", "傻", "福", "屎", "二", "胖", "臭"];
var m_name3 = ["言", "洛", "鸿", "涯", "夜", "痕", "清", "飞", "尘", "阳", "武", "遥", "风", "空", "遐", "竹", "涵", "偌", "御", "伦", "滨", "浪", "璘", "勇", "恒", "基", "明", "岚", "舜", "翰", "遐"];
// var m_name3 = ["娟", "妮", "腿", "娣", "球", "坑", "年", "岁", "娃", "毛", "剩", "姑", "英", "妹", "肥", "霞", "狗", "虎", "花", "凤", "定", "村", "蛋", "妞", "木", "翠", "爱", "财", "石", "美", "发"];

var f_name1 = ["慕容", "任", "于", "厉", "钟离", "唐", "东方", "敖", "白", "南宫", "竺", "司徒", "尉迟", "司空", "蓝", "邵", "西门", "颜", "上官", "欧阳", "尚", "莫" ];
var f_name2 = ["真", "碧", "凝", "竹", "若", "雨", "紫", "影", "亦", "伊", "允", "冰"];
var f_name3 = ["菲", "星", "芮", "悠", "馨", "香", "爱", "翎", "洛", "轩", "儿", "萱", "雪", "月", "嫣", "凌", "珣", "痕", "荫", "茹", "忆", "婷", "舞", "琦", "汐", "嫌", "钰", "心", "韵", "然", "嫣"];

 	
$(document).ready(function(){
	$( ".sex" ).change(function() {
 		if($(".sex:checked").val() == "male"){
 			// console.log("male");
 			for(n = 1; n <= 5; n++){
 				$("#"+"name_slide"+n).empty();
 				strname = "<p style='margin-top:20px;margin-left:30%;color:white;font-size:60px;'>"+generatemalename();+"</p>";
 				$("#name_slide" + n).html(strname); 
 			}
 			for(m = 1; m <= 10; m++){
 				$("#"+"avatar_slider"+m).empty();
 				stravatar = "<img style='margin-top:45px;margin-left:40%;color:white;font-size:60px;width:20%;' src='../images/m"+m+".png'>";
				$("#avatar_slider" + m).html(stravatar);
 			}
		}
		if($(".sex:checked").val() == "female"){
			// console.log("female");

 			for(n = 1; n <= 5; n++){
 				$("#"+"name_slide"+n).empty();
 				strname = "<p style='margin-top:20px;margin-left:30%;color:white;font-size:60px;'>"+generatefemalename();+"</p>";
 				$("#name_slide" + n).html(strname); 
 			}
 			for(m = 1; m <= 10; m++){
 				$("#"+"avatar_slider"+m).empty();
 				stravatar = "<img style='margin-top:45px;margin-left:40%;color:white;font-size:60px;width:20%;' src='../images/f"+m+".png'>";
				$("#avatar_slider" + m).html(stravatar);
 			}
			
		}
 	});

	$( "#submit" ).click(function(){
		if(code == con_code){
			register();

			$('#submit').hide();
			$('#submitOK').show();

			$("#name").html($(".active > p").html());
			$("#avatar").attr("src",$(".active > img").attr("src"));	
		}
		else{
			alert("两次密码输入不一致，请重新设置！");
		}
	});
})


	function register(){
		$.getJSON("http://59.77.6.18:4081/taikoWebService/account/register",{'name':$(".active > p").html(),'sex':$(".sex:checked").val(),'avatar':$(".active > img").attr("src"),'code':con_code},function(data){
			console.log("OK");
				$.cookie('player_id',data[0].id);
				$("#id").html(data[0].id);
				$.cookie('player_name',$(".active > p").html());
				$.cookie('player_sex',$(".sex:checked").val());
				$.cookie('player_avatar',$(".active > img").attr("src"));
				$.cookie('player_level',"1");				
		});
	}

	function getRandom(n){
		return Math.floor(Math.random()*n+1);
	}
	function changename(){
		if($(".sex:checked").val() == "female"){
			for(n = 1; n <= 5; n++){
				$("#"+"name_slide"+n).empty();
				strname = "<p style='margin-top:20px;margin-left:30%;color:white;font-size:60px;'>"+generatefemalename();+"</p>";
			document.getElementById("name_slide"+n).innerHTML += strname; 
			}
		}

		if($(".sex:checked").val() == "male"){
			for(n = 1; n <= 5; n++){
				$("#"+"name_slide"+n).empty();
				strname = "<p style='margin-top:20px;margin-left:30%;color:white;font-size:60px;'>"+generatemalename();+"</p>";
			document.getElementById("name_slide"+n).innerHTML += strname; 
			}
		}
	}	
	
	function generatemalename(){
		var m_ran1 = getRandom(20);
		var m_ran2 = getRandom(11);
		var m_ran3 = getRandom(30);
		var m_name = m_name1[m_ran1] + m_name2[m_ran2] + m_name3[m_ran3];
		// console.log(m_name);
		return m_name;
	}
	function generatefemalename(){
		var f_ran1 = getRandom(21);
		var f_ran2 = getRandom(11);
		var f_ran3 = getRandom(30);
		var f_name = f_name1[f_ran1] + f_name2[f_ran2] + f_name3[f_ran3];
		// console.log(f_name);
		return f_name;
	}

	