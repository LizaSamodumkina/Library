<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" scope = "session"/>
<fmt:setBundle basename="locale" var="loc"/>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
	<title>
		<fmt:message bundle="${loc}" key="local.liked" var="liked_books" />
		<c:out value = "${liked_books }"/>
	</title>
<head>
	<meta charset="utf-8">
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src = "js/likedbooks.js"></script>
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
		    	<ul class="navbar-nav mr-auto">
		      		<li class="nav-item">
		        		<a class="nav-link" href="?command=GET_CATALOG_PAGE">
		        			<fmt:message bundle="${loc}" key="local.catalog" var="catalog" />
							<c:out value = "${catalog }"/>
		        		</a>
		      		</li>
		      		<li class="nav-item dropdown active">
		        		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown10" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		        			<fmt:message bundle="${loc}" key="local.profile" var="profile" />
							<c:out value = "${profile }"/>
		        		</a>
		        		<div class="dropdown-menu" aria-labelledby="navbarDropdown10">
		          			<a class="dropdown-item" href="#">
		          				<fmt:message bundle="${loc}" key="local.liked" var="liked_books" />
								<c:out value = "${liked_books }"/>
		          			</a>
		          			<a class="dropdown-item" href="?command=GET_ORDERS_PAGE">
		          				<fmt:message bundle="${loc}" key="local.orders" var="orders" />
								<c:out value = "${orders }"/>
		          			</a>
		          			<a class="dropdown-item" href="?command=GET_ORDERS_STORY_PAGE">
		          				<fmt:message bundle="${loc}" key="local.orser_story" var="order_story" />
								<c:out value = "${order_story }"/>
		          			</a>
		          			<div class="dropdown-divider"></div>
		          			<a class="dropdown-item" href="?command=SIGN_OUT" >
		          				<fmt:message bundle="${loc}" key="local.sign_out" var="sign_out" />
								<c:out value = "${sign_out }"/>
		          			</a>
		        		</div>
		      		</li>
					 <li class="nav-item">
					 	<a class="nav-link" href="?command=EN&sessionLocale=en&current_page=LIKED_BOOKS_PAGE">
						 	<fmt:message bundle="${loc}" key="local.en" var="EN" />
						 	<c:out value = "${EN}"/>
					 	</a>
					 </li>
					 <li class="nav-item">
					 	<a class="nav-link" href="?command=RU&sessionLocale=ru&current_page=LIKED_BOOKS_PAGE">
						 	<fmt:message bundle="${loc}" key="local.ru" var="RU" />
						 	<c:out value = "${RU}"/>
					 	</a>
					 </li>
				</ul>
		  </div>
	</nav>
	
	<div id = "message" class="alert alert-light" role="alert">
		<c:out value="${sessionScope.error_message }"/>
		<c:remove var="error_message" scope="session"/>
	</div>
	
	<div class="table-responsive">
    <table class="table table-hover">
        <thead>
            <tr class="success">
                <th>
                	<fmt:message bundle="${loc}" key="local.book_name" var="name" />
					<c:out value = "${name }"/>
				</th>
                <th>
                	<fmt:message bundle="${loc}" key="local.authors" var="authors" />
					<c:out value = "${authors }"/>
				</th>
                <th>
                	<fmt:message bundle="${loc}" key="local.annotation" var="annotation" />
					<c:out value = "${annotation }"/>
                </th>
                <th>
                	<fmt:message bundle="${loc}" key="local.order" var="order" />
					<c:out value = "${order }"/>
                </th>
            </tr>
        </thead>
        <tbody id = "insertBody">
			<c:forEach var="elem" items="${list}" varStatus="status">
					<tr>
						<td><c:out value="${ elem.name }" /></td>
						<td><c:out value="${ elem.authors }" /></td>
						<td><c:out value="${ elem.annotation }" /></td>
						<td>
							<button type = "button" id = 'home_${elem.id}' class="btn button_at_liked_page">
								<fmt:message bundle="${loc}" key="local.order_to_home" var="order_to_home" />
								<c:out value = "${order_to_home }"/>
							</button>
							<br>
							<button type = "button" id = "${elem.id}" class="btn button_at_liked_page">
								<fmt:message bundle="${loc}" key="local.order_to_reading_room" var="order_to_reading_room" />
								<c:out value = "${order_to_reading_room }"/>
							</button>
							<br>
							<button type = "button" id = "delete_${elem.id}" class="btn button_at_liked_page">
								<fmt:message bundle="${loc}" key="local.delete" var="delete" />
								<c:out value = "${delete }"/>
							</button>
						</td>
					</tr>
			</c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>