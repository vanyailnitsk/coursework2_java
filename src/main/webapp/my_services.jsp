<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Мои заказы</title>
    <link rel="stylesheet" href="/styles/dashboard.css">
</head>
<body>
    <jsp:include page="main.html"/>
    <div class="card-container">
        <h2>Ваши заказы</h2>
        <c:forEach items="${services}" var="service">
            <div class="card">
                <h3>${service.description}</h3>
                <p><span>${service.amount} ₽</span></p>
                <p> Статус: <span>${service.status}</span></p>
            </div>
        </c:forEach>
    </div>
</body>
</html>
