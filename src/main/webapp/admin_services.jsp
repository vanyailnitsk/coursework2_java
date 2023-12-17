<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>История заказов</title>
    <link rel="stylesheet" href="/styles/dashboard.css">
</head>
<body>
    <jsp:include page="admin-nav.html"/>
    <div class="card-container">
        <h2>Актуальные заказы</h2>
        <c:forEach items="${services}" var="service">
            <div class="card">
                <h3>${service.description}</h3>
                <p> ID клиента <span>${service.client_id}</span></p>
                <p><span>${service.amount} ₽</span></p>
                <p> Статус: <span>${service.status}</span></p>
            </div>
        </c:forEach>
    </div>
</body>
</html>
