<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создать продукт</title>
</head>
<body>
    <jsp:include page="main.html"/>
    <form action="/create-product" method="post">
        <label for="name">Имя:</label>
        <input type="text" id="name" name="name" maxlength="50" required>
        <br>
        <label for="description">Описание</label>
        <input type="text" id="description" name="description" maxlength="50" required>
        <br>
        <label for="price">Цена:</label>
        <input type="number" id="price" name="price" required>
        <br>
        <button type="submit">Создать</button>
        <div class="message">${message}</div>
        <a href="/products">Вернуться к списку товаров</a>
    </form>
</body>
</html>
