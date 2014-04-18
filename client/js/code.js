var code = "";
var con_code = "";
$(document).ready(function(){
	for (var i = 1; i < 10 ; i++) {
		$("#switch" +i+ ">input").attr("num", i);
		$("#con_switch" +i+ ">input").attr("num", i);

		$("#switch" +i+ ">input").click(function(){			
			code = code + $(this).attr("num");
			console.log("code:" + code);
		});

		$("#con_switch" +i+ ">input").click(function(){			
			con_code = con_code + $(this).attr("num");
			console.log("con_code:" + con_code);
		});
	};

	$("#switchrepaint").click(function(){
		code = "";
		console.log("code:" + code);
		for (var i = 1; i < 10 ; i++) {
			$("#switch" +i+ ">input").prop('checked',false);
		}
	})
	$("#switchrepaint2").click(function(){
		con_code = "";
		console.log("con_code:" + con_code);
		for (var i = 1; i < 10 ; i++) {
			$("#con_switch" +i+ ">input").prop('checked',false);
		}
	})
})