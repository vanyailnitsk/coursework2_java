<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создать заказ</title>
    <link rel="stylesheet" href="/styles/products.css">
</head>
<body>
    <jsp:include page="admin-nav.html"/>
    <form action="/create-service" method="post">
        <label for="client_id">ID клиента:</label>
        <input type="number" id="client_id" name="client_id" required>
        <br>
        <label for="description">Описание:</label>
        <input type="text" id="description" name="description" maxlength="50" required>
        <br>
        <label for="status">Статус:</label>
        <input type="text" name="status" value="Оформлен" id="status" required>
        <br>
        <label for="amount">Сумма:</label>
        <input type="number" id="amount" name="amount" required>
        <br>
        <button type="submit">Создать сервис</button>
    </form>

</body>
</html>
