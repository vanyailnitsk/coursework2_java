<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Редактировать сервис</title>
    <link rel="stylesheet" href="/styles/products.css">
</head>
<body>
    <jsp:include page="main.html"/>
    <form action="/update-service" method="post">
        <input type="hidden" id="service_id" name="service_id" value="${serviceId}">

        <label for="description">Новое описание:</label>
        <input type="text" id="description" name="description" value="${service.description}" required>
        <br>

        <label for="status">Новый статус:</label>
        <input type="text" id="status" name="status" value="${service.status}" required>
        <br>

        <button type="submit">Обновить</button>
    </form>

</body>
</html>
