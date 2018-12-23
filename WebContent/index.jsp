<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" scope = "session"/>
<fmt:setBundle basename="locale" var="loc"/>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<title>Вход</title>
<head>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css"> 
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script> 
</head>
<body>

	<form action = 'Controller' method = "get">
		<input type = 'hidden' name = 'command' value = 'SIGN_IN'>
		
		<div class="form-group">
			<div class="form-row align-items-center">
				<label for="login">
					<fmt:message bundle="${loc}" key="local.login" var="Login" />
					<c:out value = "${Login }"/>
				</label>
				<div class = "col-2">
				<input name = 'login' type='text' id = "login" class="form-control form-control-sm" placeholder = "Логин">
				</div>
			</div>
			
			<br/>
			
			<div class="form-group">
			<label for = "password">
				<fmt:message bundle="${loc}" key="local.password" var="Password" />
				<c:out value = "${Password }"/>
			</label>
			<fmt:message bundle="${loc}" key="local.log_in_button" var="logIn" />
			<input name = 'password' type='password' id = "password" class="form-control form-control-sm" placeholder = "Пароль">
			</div>
		</div>
		
		<br/>
		<input type = 'submit' name = "signIn" value="${logIn}" class="btn btn-primary">
	</form>
	
	<form action = "Controller" method = "get">
		<input type = 'hidden' name = 'command' value = 'REGISTRATION'>
		
		<label for="login">
			<c:out value = "${Login }"/>
		</label>
		<input name = 'login' type='text' class="form-control form-control-sm" placeholder = "Логин">
		
		<br/>
		
		<label>
			<c:out value = "${Password }"/>
		</label>
		<input name = 'password' type='password' class="form-control form-control-sm" placeholder = "Пароль">
		
		<br/>
		
		<label>
			<fmt:message bundle="${loc}" key="local.email" var="email" />
			<c:out value = "${email }"/>
		</label>
		<input name = 'e-mail' type="email" class="form-control form-control-sm" placeholder = "E-mail">
		
		<br/>
		<fmt:message bundle="${loc}" key="local.sign_up_button" var="SignUp" />
		<input type = 'submit' name = "registration" value = "${SignUp}" class="btn btn-primary">
	</form>
	
	<form action = "Controller" method = "post">
		<input type = 'hidden' name = 'command' value = 'EN'>
		<fmt:message bundle="${loc}" key="local.en" var="EN" />
		<input type = 'submit' name = "registration" value = "${EN }" class="btn btn-primary">
	</form>
	<form action = "Controller" method = "post">
		<input type = 'hidden' name = 'command' value = 'RU'>
		<fmt:message bundle="${loc}" key="local.ru" var="RU" />
		<input type = 'submit' name = "registration" value = "${RU }" class="btn btn-primary">
	</form>
	
	 <ul>
		 <li>
		 	<a href="?sessionLocale=en">
		 	<fmt:message bundle="${loc}" key="local.en" var="EN" />
		 	<c:out value = "${EN}"/>
		 	</a>
		 </li>
		 <li>
		 	<a href="?sessionLocale=ru"><fmt:message bundle="${loc}" key="local.ru" var="RU" />
		 	<c:out value = "${RU}"/>
		 	</a>
		 </li>
	 </ul>
</body>
</html>
