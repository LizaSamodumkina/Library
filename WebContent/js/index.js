$(document).ready(function() {
	$("#entry_button").click(function () {
		/*$.ajax({
			url: 'http://localhost:8080/WebApp/Controller',
			method: 'get',
			data: getFormDataForEntry(),//данные о пользователе
			success: function (response) {
				console.log("send");
				document.write(response);
			},
			error: function (result) {
				console.log("error");
			}
		});*/
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
		$.ajax({
			url: 'http://localhost:8080/WebApp/Controller',
			method: 'get',
			data: getFormDataForRegistration(),//данные о пользователе
			success: function (response) {
				console.log("send");
				document.write(response);
			},
			error: function (result) {
				console.log("error");
			}
		});
	});

	function getFormDataForRegistration(){ //формируем данные для request
		var config = {}; //это объект
		//map по очереди рассматривает элементы массива, сформированные из данных формы (Логин + пароль)
		$("#registration_form").serializeArray().map(function(item) { //из формы формируем данные в запрос в виде "имя поля" = "значение"
			console.log(item.value);
			if (item.name === "password"){
				item.value = hex_md5(item.value);
			}
			config[item.name] = item.value;
		});
		return config;
	}
});