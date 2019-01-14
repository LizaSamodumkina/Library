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
		<c:out value = "${orders }"/>
	</title>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="css/style.css"></link>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src = "js/adminOrderStory.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark" style="background-color:#563d7c">
		  <p class="navbar-brand">
		  		<img src="img/book.png" style="width: 5%; background-color: #563d7c"/>
		  		<fmt:message bundle="${loc}" key="local.library" var="library" />
				<c:out value = "${library}"/>
		  </p>

		  <div class="collapse navbar-collapse" id="navbar10">
		    	<ul class="navbar-nav mr-auto">
		      		<li class="nav-item">
		        		<a class="nav-link" href="?command=GET_ADMIN_MAIN_PAGE">
		        			<fmt:message bundle="${loc}" key="local.pending_orders" var="pending_orders" />
							<c:out value = "${pending_orders}"/>
		        		</a>
		      		</li>
		      		<li class="nav-item active">
		        		<a class="nav-link" href="#">
		        			<fmt:message bundle="${loc}" key="local.issued_orders" var="issued_orders" />
							<c:out value = "${issued_orders}"/>
		        		</a>
		      		</li>
		      		<li class="nav-item dropdown">
		        		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown10" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		        			<fmt:message bundle="${loc}" key="local.users" var="users" />
							<c:out value = "${users}"/>
		        		</a>
		        		<div class="dropdown-menu" aria-labelledby="navbarDropdown10">
		        			<a class="dropdown-item" href="?command=GET_BLOCKED_USERS_PAGE">
		          				<fmt:message bundle="${loc}" key="local.blocked_users" var="blocked_users" />
								<c:out value = "${blocked_users }"/>
		          			</a>
		        		</div>
		      		</li>
		      		<li class="nav-item dropdown">
		      			<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown10" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		        			<fmt:message bundle="${loc}" key="local.book" var="book" />
							<c:out value = "${book }"/>
		        		</a>
		        		<div class="dropdown-menu" aria-labelledby="navbarDropdown10">
		          			<a class="dropdown-item" href="?command=GET_ADD_NEW_BOOK_PAGE">
		          				<fmt:message bundle="${loc}" key="local.add_new_book" var="add_new_book" />
								<c:out value = "${add_new_book }"/>
		          			</a>
		          			<div class="dropdown-divider"></div>
		          			<a class="dropdown-item" href="?command=GET_DELETE_BOOK_PAGE">
		          				<fmt:message bundle="${loc}" key="local.delete_book" var="delete_book" />
								<c:out value = "${delete_book }"/>
		          			</a>
		        		</div>
		      		</li>
		      		<li class="nav-item">
		      			<a class="nav-link" href="?command=SIGN_OUT">
		          			<fmt:message bundle="${loc}" key="local.sign_out" var="sign_out" />
							<c:out value = "${sign_out }"/>
		          		</a>
		      		</li>
		      		
		      		
					 <li class="nav-item">
					 	<a class="nav-link" href="?command=EN&sessionLocale=en&current_page=ADMIN_ORDER_STORY_PAGE">
					 	<fmt:message bundle="${loc}" key="local.en" var="EN" />
					 	<c:out value = "${EN}"/>
					 	</a>
					 </li>
					 <li class="nav-item">
					 	<a class="nav-link" href="?command=RU&sessionLocale=ru&current_page=ADMIN_ORDER_STORY_PAGE">
					 	<fmt:message bundle="${loc}" key="local.ru" var="RU" />
					 	<c:out value = "${RU}"/>
					 	</a>
					 </li>
				</ul>
		  </div>
	</nav>
	
	<form class="form-inline my-2 search_div">
			<fmt:message bundle="${loc}" key="local.search_by_user_name" var="search_by_user_name" />
      		<input class="form-control mr-sm-2" type="search" id = "userNameForSerach" placeholder="${search_by_user_name}" aria-label="Search">
      		<button class="btn search_button" type="button" id = "userSearch">
				<c:out value = "${search_by_user_name }"/>
			</button>
			<button class="btn reset_button" type="button" id = "reset">
				<fmt:message bundle="${loc}" key="local.reset" var="reset" />
				<c:out value = "${reset }"/>
			</button>
    </form>

	<div class="table-responsive">
    <table class="table table-hover table-condensed">
        <thead>
            <tr class="success">
                <th>
                	№
				</th>
                <th>
                	<fmt:message bundle="${loc}" key="local.user" var="user" />
					<c:out value = "${user }"/>
				</th>
                <th>
                	<fmt:message bundle="${loc}" key="local.book" var="book" />
					<c:out value = "${book }"/>
                </th>
                <th>
                	<fmt:message bundle="${loc}" key="local.order_date" var="order_date" />
					<c:out value = "${order_date }"/>
                </th>
                <th>
                	<fmt:message bundle="${loc}" key="local.date_of_expiry" var="date_of_expiry" />
					<c:out value = "${date_of_expiry }"/>
                </th>
                <th>
                	<fmt:message bundle="${loc}" key="local.is_finished" var="is_finished" />
					<c:out value = "${is_finished }"/>
                </th>
            </tr>
        </thead>
        <tbody id = "insertBody">
			<c:forEach var="elem" items="${list}" varStatus="status">
					<tr>
						<td><c:out value="${ elem.id }" /></td>
						<td><c:out value="${ elem.user.login }" /></td>
						<td>
							"<c:out value="${ elem.book.name }" />",
							<c:out value="${ elem.book.authors }" />
						</td>
						<td><c:out value="${ elem.orderDate }" /></td>
						<td>
							<c:out value="${ elem.dateOfExpiry }" />
						</td>
						<td>
							<c:choose>
								<c:when  test="${elem.isReplace eq 1}">
									<fmt:message bundle="${loc}" key="local.yes" var="yes" />
									<c:out value = "${yes }"/>
								</c:when>
								<c:when  test="${elem.isReplace eq 0}">
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