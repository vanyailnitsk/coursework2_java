<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Обращения</title>
    <link rel="stylesheet" type="text/css" href="/styles/appointments.css">
</head>
<body>
    <jsp:include page="main.html"/>
    <h2>Ваши заявки</h2>
    <table>
        <tr>
            <th>Проблема</th>
            <th>Дата</th>
        </tr>
        <c:forEach items="${appointments}" var="appointment">
            <tr>
                <td>${appointment.problem}</td>
                <td>${appointment.dateFormatted}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
</body>
</html>

