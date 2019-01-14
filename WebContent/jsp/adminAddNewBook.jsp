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
		<fmt:message bundle="${loc}" key="local.blocked_users" var="blocked_users" />
		<c:out value = "${blocked_users }"/>
	</title>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="css/style.css"></link>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script src = "js/adminAddNewBook.js"></script>
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
		      		<li class="nav-item">
		        		<a class="nav-link" href="?command=GET_ADMIN_ORDER_STORY_PAGE">
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
		        			<a class="dropdown-item" href="#">
		          				<fmt:message bundle="${loc}" key="local.blocked_users" var="blocked_users" />
								<c:out value = "${blocked_users }"/>
		          			</a>
		        		</div>
		      		</li>
		      		<li class="nav-item dropdown active">
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
					 	<a class="nav-link" href="?command=EN&sessionLocale=en&current_page=ADMIN_ADD_NEW_BOOK_PAGE">
					 	<fmt:message bundle="${loc}" key="local.en" var="EN" />
					 	<c:out value = "${EN}"/>
					 	</a>
					 </li>
					 <li class="nav-item">
					 	<a class="nav-link" href="?command=RU&sessionLocale=ru&current_page=ADMIN_ADD_NEW_BOOK_PAGE">
					 	<fmt:message bundle="${loc}" key="local.ru" var="RU" />
					 	<c:out value = "${RU}"/>
					 	</a>
					 </li>
				</ul>
		  </div>
	</nav>
	
	<div id = "message" class="alert alert-light" role="alert">
		<c:out value="${error_message }"/>
		<c:remove var="error_message" scope="session"/>
	</div>
	
	<div class="container">
		<form class="form-horizontal" id = "bookInfo">
			<div class="form-group">
				<div class="form-row align-items-center">
					<label for="name">
						<fmt:message bundle="${loc}" key="local.book_name" var="book_name" />
						<c:out value = "${book_name}"/>
					</label>
					<div class = "col-5 book_form_name">
						<input name = 'name' type='text' id = "name" class="form-control form-control-sm" placeholder = "${book_name}" autofocus>
					</div>
				</div>
					
					<br/>
					
				<div class="form-row align-items-center">
					<label for="authors">
						<fmt:message bundle="${loc}" key="local.authors" var="authors" />
						<c:out value = "${authors}"/>
					</label>
					<div class = "col-5 book_form_author">
						<input name = 'authors' type='text' id = "authors" class="form-control form-control-sm" placeholder = "${authors}">
					</div>
				</div>
					
					<br/>
				
				<div class="form-row align-items-center">
					<label for="annotation">
						<fmt:message bundle="${loc}" key="local.annotation" var="annotation" />
						<c:out value = "${annotation}"/>
					</label>
					<div class = "col-5 book_form_annotation">
						<textarea rows="3" class = "md-textarea form-control" id = "annotation"></textarea>
					</div>
				</div>
					
					<br/>
				
				<div class="form-row align-items-center">
					<label for="publisher">
						<fmt:message bundle="${loc}" key="local.publisher" var="publisher" />
						<c:out value = "${publisher}"/>
					</label>
					<div class = "col-5 book_form_publisher">
						<input name = 'publisher' type='text' id = "publisher" class="form-control form-control-sm" placeholder = "${publisher}">
					</div>
				</div>
					
					<br/>
					
				<div class="form-row align-items-center">
					<label for="publishing_year">
						<fmt:message bundle="${loc}" key="local.publishing_year" var="publishing_year" />
						<c:out value = "${publishing_year}"/>
					</label>
					<div class = "col-5 book_form_year">
						<input name = 'publishing_year' type='text' id = "publishing_year" class="form-control form-control-sm" placeholder = "${publishing_year}">
					</div>
				</div>
				
				<br/>
				
				<div class="form-row align-items-center">
					<label for="page_num">
						<fmt:message bundle="${loc}" key="local.page_num" var="page_num" />
						<c:out value = "${page_num}"/>
					</label>
					<div class = "col-5 book_form_page_num">
						<input name = 'page_num' type='text' id = "page_num" class="form-control form-control-sm" placeholder = "${page_num}">
					</div>
				</div>
				
				<br/>
					
				<div class="form-row align-items-center">
					<label for="number_of_copies">
						<fmt:message bundle="${loc}" key="local.number_of_copies" var="number_of_copies" />
						<c:out value = "${number_of_copies}"/>
					</label>
					<div class = "col-5 book_form_copy_num">
						<input name = 'number_of_copies' type='text' id = "number_of_copies" class="form-control form-control-sm" placeholder = "${number_of_copies}">
					</div>
				</div>
					
					<br/>
					
					<button type = "button" id = "add" class="add_button add_book_button" >
						<fmt:message bundle="${loc}" key="local.add" var="add" />
						<c:out value = "${add }"/>
					</button>
			</div>
		</form>
	</div>
</body>
</html>