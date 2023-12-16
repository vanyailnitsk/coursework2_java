<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Обращения</title>
    <link rel="stylesheet" type="text/css" href="/styles/appointments.css">
</head>
<body>
    <jsp:include page="main.html"/>
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
    <form action="/appointments" method="post">
        <label for="problem">Проблема</label>
        <input type="text" id="problem" name="problem" maxlength="50" required>
        <br>
        <button type="submit">Создать</button>
    </form>
</body>
</html>

