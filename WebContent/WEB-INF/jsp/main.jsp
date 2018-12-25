<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" scope = "session"/>
<fmt:setBundle basename="locale" var="loc"/>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
	<title>Каталог</title>
<head>
	<meta charset="utf-8">
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src = "js/main.js"></script>
</head>
<body>
<!--table-responsive сделает таблицу более адаптивной (в bootstrap все адаптивное, но это делает еще лучше), при очень маленьком
размере экрана появятся скролы, чтобы ее прокручивать. Это свойство обязательно нужно писать в какой-то div, а не в тег table-->
	<div class="table-responsive">
    <!--table делает таблицу растянутой по странице и визуальнее красивее стандартной-->
    <!--table-hover подсвечивает строку таблицы при наведении-->
    <!--table-bordered делает границы между ячейками-->
    <!--table-striped делает чередование цветов строк-->
    <!--table-condensed если таблица большая, то удобно применить, тк отступы станут меньше -->
    <table class="table table-hover">
        <thead>
        <!--далее будут классы и для текста подходить, а не только для ячеек таблиц-->
        <!--active сделает цвет заголовка серым-->
        <!--success сделает цвет фона зеленым-->
        <!--info сделает цвет фона синим-->
        <!--warning сделает цвет фона цвет желтым-->
        <!--danger сделает цвет фона красным -->
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
                	<fmt:message bundle="${loc}" key="local.description" var="description" />
					<c:out value = "${description }"/>
                </th>
                <th>
                	<fmt:message bundle="${loc}" key="local.add" var="add" />
					<c:out value = "${add }"/>
                </th>
            </tr>
        </thead>
        <tbody id = "insertBody">
			<c:forEach var="elem" items="${list}" varStatus="status">
					<tr>
						<td><c:out value="${ elem.name }" /></td>
						<td><c:out value="${ elem.authors }" /></td>
						<td><c:out value="${ elem.annotation }" /></td>
						<td><c:out value="${ elem.description }" /></td>
						<td><button type = "button" id = "${elem.id}">Добавить в личный кабинет</button></td>
					</tr>
			</c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>