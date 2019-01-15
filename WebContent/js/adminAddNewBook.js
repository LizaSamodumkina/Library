$("#message").css("display", "none");

$(document).ready(function(){
	$("#add").click(function(){
		$.ajax({
			url: 'http://localhost:8080/WebApp/Controller',
			method: 'post',
			data: bookInfo(),
			success: function (response) {
				console.log("send");
				$("#message").css("display", "inline");
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
	
	function bookInfo(){
		var config = {};
		
		config["command"] = "ADD_NEW_BOOK";
		config["bookName"] = $("#name").val();
		config["authors"] = $("#authors").val();
		config["annotation"] = $("#annotation").val();
		config["publisher"] = $("#publisher").val();
		config["publishing_year"] = $("#publishing_year").val();
		config["page_num"] = $("#page_num").val();
		config["copyNum"] = $("#number_of_copies").val();
		
		return config;
	}
});
