<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" scope = "session"/>
<fmt:setBundle basename="locale" var="loc"/>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<title>
	<fmt:message bundle="${loc}" key="local.welcome" var="welcome" />
	<c:out value = "${welcome }"/>
</title>
<head>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="css/style.css"></link>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark" style="background-color:#563d7c">
		  <p class="navbar-brand">
		  		<img src="img/book.png" style="width: 5%; background-color: #563d7c"/>
		  		<fmt:message bundle="${loc}" key="local.library" var="library" />
				<c:out value = "${library }"/>
		  </p>

		  <div class="collapse navbar-collapse" id="navbar10">
		    	<ul class="navbar-nav">
					 <li class="nav-item">
						 <a class="nav-link" href="?command=EN&sessionLocale=en&current_page=CATALOG_PAGE">
						 	<fmt:message bundle="${loc}" key="local.en" var="EN" />
					 		<c:out value = "${EN}"/>
					 	</a>
					 </li>
					 <li class="nav-item">
					 	<a class="nav-link" href="?command=RU&sessionLocale=ru&current_page=CATALOG_PAGE">
						 	<fmt:message bundle="${loc}" key="local.ru" var="RU" />
						 	<c:out value = "${RU}"/>
					 	</a>
					 </li>
				</ul>
		  </div>
	</nav>

	<c:out value="${requestScope.error_message }"/>
	
	<div class="container">
	<form action = 'Controller' method = "get" id = "entry_form" class="form-horizontal">
		<input type = 'hidden' name = 'command' value = 'SIGN_IN'>
		
		<div class="form-group">
			<div class="form-row align-items-center">
				<label for="login">
					<fmt:message bundle="${loc}" key="local.login" var="Login" />
					<c:out value = "${Login}"/>
				</label>
				<div class = "col-2">
					<input name = 'login' type='text' id = "login" class="form-control form-control-sm" placeholder = "${Login}">
				</div>
			</div>
			
			<br/>
			
			<div class="form-row align-items-center">
				<label for = "password">
					<fmt:message bundle="${loc}" key="local.password" var="Password" />
					<c:out value = "${Password}"/>
				</label>
					<div class = "col-2">
					<input name = 'password' type='password' id = "password" class="form-control form-control-sm" placeholder = "${Password}">
				</div>
			</div>
		</div>
		
		<br/>
		<fmt:message bundle="${loc}" key="local.log_in_button" var="logIn" />
		<input type = 'button' name = "signIn" id = "entry_button" value="${logIn}" class="btn button_style">
	</form>
	</div>
	
	<form action = "Controller" method = "get" id = "registration_form">
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
		<input type = 'button' name = "registration" id = "registration" value = "${SignUp}" class="btn">
	</form>
	
	<!--<form action = "Controller" method = "post">
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
		 	<a href="?sessionLocale=en&current_page=MAIN_PAGE">
		 	<fmt:message bundle="${loc}" key="local.en" var="EN" />
		 	<c:out value = "${EN}"/>
		 	</a>
		 </li>
		 <li>
		 	<a href="?sessionLocale=ru&current_page=MAIN_PAGE"><fmt:message bundle="${loc}" key="local.ru" var="RU" />
		 	<c:out value = "${RU}"/>
		 	</a>
		 </li>
	 </ul> -->
	<script type="text/javascript" src="js/md5-min.js"></script>
	<script src = "js/index.js"></script>
</body>
</html>
