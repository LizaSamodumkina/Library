<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>Вход</title>
<body>
	<form action = 'Controller' method = "post">
		<input type = 'hidden' name = 'command' value = 'SIGN_IN'>
		
		<label>Логин</label>
		<input name = 'login' type='text' placeholder = "Логин">
		
		<br/>
		
		<label>Пароль</label>
		<input name = 'password' type='password' placeholder = "Пароль">
		
		<br/>
		<button type = 'submit' name = "signIn">Вход</button>
	</form>
	
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
