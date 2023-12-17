<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет</title>
    <link rel="icon" href="/img/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="/styles/dashboard.css">
</head>
<body>
    <jsp:include page="main.html"/>
    <div class="content">
        <h1>Добро пожаловать в личный кабинет, ${client.name}!</h1>
        <h3 style="text-align: center">Ваш номер телефона: ${client.contact}</h3>
        <form action="/appointments" method="post">
            <label for="problem">Проблема</label>
            <input type="text" id="problem" name="problem" maxlength="50" required>
            <br>
            <button type="submit">Отправить</button>
        </form>
        <div class="card-container">
            <h2>Актуальные заказы</h2>
            <c:forEach items="${services}" var="service">
                <div class="card">
                    <h3>${service.description}</h3>
                    <p><span>${service.amount} ₽</span></p>
                    <p> Статус: <span>${service.status}</span></p>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
