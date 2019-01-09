$(document).ready(function(){
	
	$("#userSearch").click(function(){		
		var url = "http://localhost:8080/WebApp/Controller?command=";
		url += "TAKE_ADMIN_ORDER_STORY";
		url += "&userSearch=";
		url += $("#userNameForSerach").val();
		document.location.href = url;
	});
	
	$("#reset").click(function(){		
		var url = "http://localhost:8080/WebApp/Controller?command=";
		url += "TAKE_ADMIN_ORDER_STORY";
		document.location.href = url;
	});
});