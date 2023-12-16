<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>История расходов</title>
    <link rel="stylesheet" href="/styles/products.css">
    <link rel="icon" href="/img/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="/styles/main.css">
    <link rel="stylesheet" href="/styles/userCabinet.css">
</head>
<body>
    <jsp:include page="main.html"/>
    <div class="content">
        <div class="recent-purchases">
            <h2>Список доступных товаров</h2>
            <table>
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Описание</th>
                    <th>Цена</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <script src="/scripts/product.js"></script>
</body>
</html>
