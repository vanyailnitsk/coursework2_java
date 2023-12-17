<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Панель администратора</title>
</head>
<body>
    <jsp:include page="admin-nav.html"/>
    <table>
        <tr>
            <th>Id клиента</th>
            <th>Контактные данные клиента</th>
            <th>Проблема</th>
            <th>Дата</th>
            <th></th>
        </tr>
        <c:forEach items="${appointments}" var="appointment">
            <tr>
                <td>${appointment.clientId}</td>
                <td>${appointment.contact}</td>
                <td>${appointment.problem}</td>
                <td>${appointment.dateFormatted}</td>
                <td>
                    <form id="create-service" action="">
                        <button type="button" onclick="submitDelete(${appointment.appointmentId})">Создать</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
