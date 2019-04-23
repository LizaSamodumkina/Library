$(document).ready(function() {
	$("#b_signIn").click(function(){
		$("#entry_form").css("display", "inline");
		$("#registration_form").css("display", "none");
		$("#b_registration").css("opacity", "0.5");
		$("#b_signIn").css("opacity", "1");
	});
	
	$("#b_registration").click(function(){
		$("#registration_form").css("display", "inline");
		$("#entry_form").css("display", "none");
		$("#b_signIn").css("opacity", "0.5");
		$("#b_registration").css("opacity", "1");
	});
	
	$("#entry_button").click(function () {
		
		var url = "http://localhost:8080/WebApp/Controller?command=SIGN_IN&";
		$("#entry_form").serializeArray().map(function(item) { //из формы формируем данные в запрос в виде "имя поля" = "значение"
			if (item.name === "login"){
				url += "login=" + item.value + "&";
			}
			if (item.name === "password"){
				item.value = hex_md5(item.value);
				url += "password=" + item.value + "&";
			}
		});
		document.location.href = url;
	});

	function getFormDataForEntry(){ //формируем данные для request
		var config = {}; //это объект
		config["command"] = "SIGN_IN";
		//map по очереди рассматривает элементы массива, сформированные из данных формы (Логин + пароль)
		$("#entry_form").serializeArray().map(function(item) { //из формы формируем данные в запрос в виде "имя поля" = "значение"
			if (item.name === "password"){
				item.value = hex_md5(item.value);
			}
			config[item.name] = item.value;
		});
		return config;
	}

	$("#registration").click(function () {
				
		var url = "http://localhost:8080/WebApp/Controller?command=REGISTRATION&";
		$("#registration_form").serializeArray().map(function(item) { //из формы формируем данные в запрос в виде "имя поля" = "значение"
			if (item.name === "login"){
				url += "login=" + item.value + "&";
			}
			if (item.name === "password"){
				item.value = hex_md5(item.value);
				url += "password=" + item.value + "&";
			}
			if (item.name === "e-mail"){
				url += "e-mail=" + item.value + "&";
			}
		});
		document.location.href = url;
	});

	function getFormDataForRegistration(){ //формируем данные для request
		var config = {}; //это объект
		//map по очереди рассматривает элементы массива, сформированные из данных формы (Логин + пароль)
		$("#registration_form").serializeArray().map(function(item) { //из формы формируем данные в запрос в виде "имя поля" = "значение"
			
			if (item.name === "password"){
				item.value = hex_md5(item.value);
			}
			config[item.name] = item.value;
		});
		return config;
	}
});
