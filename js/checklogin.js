var checkid = "";
var con_checkcode = "";
$(document).ready(function(){
	for (var i = 1; i < 10 ; i++) {
		$("#id_switch" +i+ ">input").attr("num", i);
		$("#conid_switch" +i+ ">input").attr("num", i);

		$("#id_switch" +i+ ">input").click(function(){			
			checkid = checkid + $(this).attr("num");
			console.log("checkid:" + checkid);
		});

		$("#conid_switch" +i+ ">input").click(function(){			
			con_checkcode = con_checkcode + $(this).attr("num");
			console.log("con_checkcode:" + con_checkcode);
		});
	};

	$("#id_switchrepaint").click(function(){
		checkid = "";
		console.log("checkid:" + checkid);
		for (var i = 1; i < 10 ; i++) {
			$("#id_switch" +i+ ">input").prop('checked',false);
		}
	})
	$("#id_switchrepaint2").click(function(){
		con_checkcode = "";
		console.log("con_checkcode:" + con_checkcode);
		for (var i = 1; i < 10 ; i++) {
			$("#conid_switch" +i+ ">input").prop('checked',false);
		}
	})

	
	$( "#loginsubmit" ).click(function(){
		$('#loginsubmit').hide();
		$('#loginsubmitOK').show();
	});
})