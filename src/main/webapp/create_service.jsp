<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
        <input type="hidden" value="Оформлен" id="status">
        <br>
        <label for="amount">Сумма:</label>
        <input type="number" id="amount" name="amount" required>
        <br>
        <button type="submit">Создать сервис</button>
    </form>

</body>
</html>
