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
		<fmt:message bundle="${loc}" key="local.orders" var="orders" />
		<c:out value = "${orders}"/>
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
		          			<a class="dropdown-item" href="?command=GET_LIKED_BOOKS_PAGE">
		          				<fmt:message bundle="${loc}" key="local.liked" var="liked_books" />
								<c:out value = "${liked_books }"/>
		          			</a>
		          			<a class="dropdown-item" href="#">
		          				<fmt:message bundle="${loc}" key="local.orders" var="orders" />
								<c:out value = "${orders }"/>
		          			</a>
		          			<a class="dropdown-item" href="?command=GET_ORDERS_STORY_PAGE">
		          				<fmt:message bundle="${loc}" key="local.orser_story" var="orser_story" />
								<c:out value = "${orser_story }"/>
		          			</a>
		          			<div class="dropdown-divider"></div>
		          			<a class="dropdown-item" href="?command=SIGN_OUT">
		          				<fmt:message bundle="${loc}" key="local.sign_out" var="sign_out" />
								<c:out value = "${sign_out }"/>
		          			</a>
		        		</div>
		      		</li>
					 <li class="nav-item">
					 	<a class="nav-link" href="?command=EN&sessionLocale=en&current_page=ORDERS_PAGE">
						 	<fmt:message bundle="${loc}" key="local.en" var="EN" />
						 	<c:out value = "${EN}"/>
					 	</a>
					 </li>
					 <li class="nav-item">
					 	<a class="nav-link" href="?command=RU&sessionLocale=ru&current_page=ORDERS_PAGE">
						 	<fmt:message bundle="${loc}" key="local.ru" var="RU" />
						 	<c:out value = "${RU}"/>
					 	</a>
					 </li>
				</ul>
		  </div>
	</nav>
	
	<div class="table-responsive">
    <table class="table table-hover">
        <thead>
            <tr class="success">
                <th>
                	№
				</th>
                <th>
                	<fmt:message bundle="${loc}" key="local.book" var="book" />
					<c:out value = "${book}"/>
				</th>
                <th>
                	<fmt:message bundle="${loc}" key="local.order_date" var="order_date" />
					<c:out value = "${order_date}"/>
                </th>
                <th>
                	<fmt:message bundle="${loc}" key="local.date_of_expiration" var="date_of_expiration" />
					<c:out value = "${date_of_expiration}"/>
                </th>
                <th>
                	<fmt:message bundle="${loc}" key="local.expired" var="expired" />
					<c:out value = "${expired}"/>
                </th>
            </tr>
        </thead>
        <tbody id = "insertBody">
			<c:forEach var="elem" items="${list}" varStatus="status">
					<tr>
						<td><c:out value="${ elem.id }" /></td>
						<td>
							"<c:out value="${ elem.book.name }" />",
							<c:out value="${ elem.book.authors }" />
						</td>
						<td>
							<c:out value="${ elem.orderDate }" />
						</td>
						<td>
							<c:out value="${ elem.dateOfExpiry }" />
						</td>
						<td>
							<c:choose>
								<c:when  test="${elem.isExpired eq 1}">
									<fmt:message bundle="${loc}" key="local.yes" var="yes" />
									<c:out value = "${yes }"/>
								</c:when>
								<c:when  test="${elem.isExpired eq 0}">
									<fmt:message bundle="${loc}" key="local.no" var="no" />
									<c:out value = "${no }"/>
								</c:when>
							</c:choose>
						</td>
					</tr>
			</c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>