$(document).ready(function(){
	$(document).on("click","button",function(){
		$.ajax({
			url: 'http://localhost:8080/WebApp/Controller',
			method: 'post',
			data: userInfo(this.id),
			success: function (response) {
				console.log("send");
				document.write(response);
				document.close();
				document.open();
				//window.location.reload();
			},
			error: function (result) {
				console.log("error");
				$("#message").css("display", "inline");
				//window.location.reload(); //перезагрузка
			}
		});
	});
	
	function userInfo(login){
		var config = {};
		
		config["command"] = "DELETE_BLOKED_USER";
		config["login"] = login;
		
		return config;
	}
});