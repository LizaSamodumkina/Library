<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Регистрация</title>
</head>
<body>
	<h2>Данного пользователя нет в системе.</h2>
	
	<form action = "Controller" method = "post">
		<input type = 'hidden' name = 'command' value = 'REGISTRATION'>
		
		<label>Логин</label>
		<input name = 'login' type='text' placeholder = "Логин">
		
		<br/>
		
		<label>Пароль</label>
		<input name = 'password' type='password' placeholder = "Пароль">
		
		<br/>
		
		<label>E-mail</label>
		<input name = 'e-mail' type='e-mail' placeholder = "E-mail">
		
		<br/>
		
		<button type = 'submit' value = "registration">Регистрация</button>
	</form>
</body>
</html>