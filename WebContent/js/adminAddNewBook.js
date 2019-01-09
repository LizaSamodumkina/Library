$(document).ready(function(){
	$("#add").click(function(){
		alert("yes");
		$.ajax({
			url: 'http://localhost:8080/WebApp/Controller',
			method: 'post',
			data: bookInfo(),
			success: function (response) {
				console.log("send");
				//window.location.reload();
			},
			error: function (result) {
				console.log("error");
				//window.location.reload(); //перезагрузка
			}
		});
	});
	
	function bookInfo(){
		var config = {};
		
		config["command"] = "ADD_NEW_BOOK";
		config["bookName"] = $("#name").val();
		config["authors"] = $("#authors").val();
		config["annotation"] = $("#annotation").val();
		config["description"] = $("#description").val();
		config["copyNum"] = $("#number_of_copies").val();
		
		return config;
	}
});