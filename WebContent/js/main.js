$(document).ready(function() {
	$(document).on("click","button",function(){
		$.ajax({
			url: 'http://localhost:8080/WebApp/Controller',
			method: 'post',
			data: bookIdForRequest(this.id),//данные о пользователе
			success: function (response) {
				console.log("send");
				document.write(response);
				document.close();
				document.open();
			},
			error: function (result) {
				console.log("error");
			}
		});
	});
	
	function bookIdForRequest(bookId){
		var config = {};
		config["command"] = "ADD_AS_LIKED_BOOK";
		config["bookId"] = bookId;
		return config;
	}
});
