<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Панель администратора</title>
    <link rel="stylesheet" href="/styles/products.css">
</head>
<body>
    <jsp:include page="admin-nav.html"/>
    <table>
        <tr>
            <th>Id клиента</th>
            <th>Контактные данные клиента</th>
            <th>Проблема</th>
            <th>Дата</th>
        </tr>
        <c:forEach items="${appointments}" var="appointment">
            <tr>
                <td>${appointment.clientId}</td>
                <td>${appointment.contact}</td>
                <td>${appointment.problem}</td>
                <td>${appointment.dateFormatted}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
