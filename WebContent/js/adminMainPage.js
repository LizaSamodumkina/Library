$(document).ready(function(){
	$("#home").click(function(){
		var url = "http://localhost:8080/WebApp/Controller?command=";
		url += "NEED_SEND_OUT_BOOKS_TO_HOME";
		document.location.href = url;
	});
	
	$("#readingroom").click(function(){
		var url = "http://localhost:8080/WebApp/Controller?command=";
		url += "NEED_SEND_OUT_BOOKS_TO_READING_ROOM";
		document.location.href = url;
	});
	
	$("#userSearch").click(function(){		
		var url = "http://localhost:8080/WebApp/Controller?command=";
		if (($("#readingroom").css("opacity")) == 1){
			url += "NEED_SEND_OUT_BOOKS_TO_READING_ROOM";
		}else{
			url += "NEED_SEND_OUT_BOOKS_TO_HOME";
		}
		url += "&userSearch=";
		url += $("#userNameForSerach").val();
		document.location.href = url;
	});
	
	$("#reset").click(function(){		
		var url = "http://localhost:8080/WebApp/Controller?command=";
		if (($("#readingroom").css("opacity")) == 1){
			url += "NEED_SEND_OUT_BOOKS_TO_READING_ROOM";
		}else{
			url += "NEED_SEND_OUT_BOOKS_TO_HOME";
		}
		document.location.href = url;
	});
	
	$(document).on("click","button",function(){
		if (this.id === "reset" || this.id === "userSearch" || this.id === "readingroom" || this.id === "home"){
			return;
		}else{
			$.ajax({
				url: 'http://localhost:8080/WebApp/Controller',
				method: 'post',
				data: createOrder(this.id),//данные о пользователе
				success: function (response) {
					console.log("send");
					window.location.reload();
				},
				error: function (result) {
					console.log("error");
					window.location.reload(); //перезагрузка
				}
			});
		}
	});
	
	function createOrder(id){
		var config = {};
		
		config["command"] = "CREATE_ORDER";
		if (($("#readingroom").css("opacity")) == 1){
			config["home"] = "false";
		}else{
			config["home"] = "true";
		}
		
		config["id"] = id;
		
		return config;
	}
});