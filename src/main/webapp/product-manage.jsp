<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Продукты</title>
    <link rel="stylesheet" href="/styles/products.css">
</head>
<body>
    <jsp:include page="admin-nav.html"/>
    <form action="/products-manage" method="post">
        <label for="name">Название:</label>
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
    </form>
    <div class="content">
        <div class="recent-purchases">
            <h2>Список доступных товаров</h2>
            <table>
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Описание</th>
                    <th>Цена</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                        <td>
                            <form id="delete">
                                <button type="button" onclick="submitDelete(${product.productId})">Удалить</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <script>
        function submitDelete(productId) {
            event.preventDefault();
            var form = document.getElementById('delete');
            fetch('/products-manage?id='+productId, {
                method: 'DELETE',
            })
                .then(data => {
                    location.reload()
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        }
    </script>
</body>
</html>
