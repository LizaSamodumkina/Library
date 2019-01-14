$(document).ready(function() {
	$(document).on("click","button",function(){
		$.ajax({
			url: 'http://localhost:8080/WebApp/Controller',
			method: 'post',
			data: bookIdForRequest(this.id),
			success: function (response) {
				window.location.reload();
			},
			error: function (result) {
				window.location.reload();
			}
		});
	});
	
	function bookIdForRequest(bookId){
		var config = {};
		var prefix = bookId.substring(0,4);
		var likedId = "";
		if (prefix === "home"){
			config["command"] = "TAKE_BOOK_TO_HOME";
			likedId = bookId.substring(5, bookId.length);
		} else {
			if (prefix === 'dele'){
				config["command"] = "DELETE_LIKED_BOOK";
				likedId = bookId.substring(7, bookId.length);
			} else{
				config["command"] = "TAKE_BOOK_TO_READING_ROOM";
				likedId = bookId;
			}
		}
		config["likedBookId"] = likedId;
		return config;
	}
	
	$("#signOut").click(function(){
		$.ajax({
			url: 'http://localhost:8080/WebApp/Controller',
			method: 'post',
			data: signOutData(),
			success: function (response) {
				console.log(response);
				document.write(response);
				//window.location.reload();
			},
			error: function (result) {
				//window.location.reload();
			}
		});
	});
	
	function signOutData(){
		var config = {};
		
		config["command"] = "SIGN_OUT";
		
		return config;
	}
});