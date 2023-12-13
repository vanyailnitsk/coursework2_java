<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>История расходов</title>
    <link rel="stylesheet" href="/styles/finance-history.css">
    <link rel="icon" href="/img/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="/styles/main.css">
    <link rel="stylesheet" href="/styles/userCabinet.css">
</head>
<body>
    <jsp:include page="main.html"/>
    <div class="content">
        <button class="create-button" onclick="openCreateIncomeModal()">Добавить запись о доходе</button>
        <div class="recent-purchases">
            <h2>Последняя информация о доходах</h2>
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
<%--                        <td>--%>
<%--                            <button class="edit-button"--%>
<%--                                    onclick="openEditIncomeModal(${product.id},${product.amount},'${product.source}')"--%>
<%--                            >Изменить--%>
<%--                            </button>--%>
<%--                            <button class="delete-button" type="submit" onclick="deleteIncome(${product.id})">Удалить--%>
<%--                            </button>--%>
<%--                        </td>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div id="createIncomeModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeCreateIncomeModal()">&times;</span>
            <h2>Добавить запись о доходе</h2>
            <form id="createIncomeForm">
                <input type="hidden" id="user_id" name="user_id" required value="${userId}">
                <label for="amount">Сумма:</label>
                <input type="number" id="amount" name="amount" required>

                <label for="source">Источник:</label>
                <input type="text" id="source" name="source" maxlength="50" required>

                <label for="category_id">Категория:</label>
                <select id=category_id name="category_id">
                    <option value="0">Выберите категорию</option>
                    <c:forEach items="${income_categories}" var="service">
                        <option value="${service.id}">${service.name}</option>
                    </c:forEach>
                </select>
                <button type="submit">Создать</button>
                <div id="message"></div>
            </form>
        </div>
    </div>
    <div id="editIncomeModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeEditIncomeModal()">&times;</span>
            <h2>Редактировать запись о доходе</h2>
            <form id="editIncomeForm">
                <input type="hidden" id="edit-product-id" name="user_id" required>
                <label for="amount">Сумма:</label>
                <input type="number" id="edit-amount" name="amount" required value="">

                <label for="source">Источник:</label>
                <input type="text" id="edit-source" name="source" maxlength="50" required>

                <label for="category_id">Категория:</label>
                <select id="edit-service-id" name="category_id">
                    <option value="0">Выберите категорию</option>
                    <c:forEach items="${income_categories}" var="service">
                        <option value="${service.id}">${service.name}</option>
                    </c:forEach>
                </select>
                <button type="submit">Сохранить</button>
                <div id="edit-message"></div>
            </form>
        </div>
    </div>
    <script src="/scripts/product.js"></script>
</body>
</html>
