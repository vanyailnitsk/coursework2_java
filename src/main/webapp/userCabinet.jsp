<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет</title>
    <link rel="icon" href="/img/logo.png" type="image/x-icon">
    <link rel="shortcut icon" href="/img/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="/styles/userCabinet.css">
    <link rel="stylesheet" href="/styles/navbar.css">
    <link rel="stylesheet" href="/styles/main.css">
</head>
<body>
    <jsp:include page="main.html"/>
    <div class="content">
        <h1>Добрый день, ${client.name}!</h1>
        <div class="categories-container">
            <c:forEach items="${expense_categories}" var="category_map">
                <div class="service-card"
                     onclick="openCreateExpenseModal('${category_map.key.name}',${category_map.key.id})">
                    <img class="service-image" src="/img/service.png" alt="${category_map.key.name}">
                    <div class="service-title">${category_map.key.name}</div>
                    <div class="service-amount">${category_map.value} ₽</div>
                </div>
            </c:forEach>
        </div>
        <div class="card-container">
            <h2>Ваши последние покупки</h2>
            <c:forEach items="${expens}" var="appointment">
                <div class="card">
                    <h3>${appointment.source}</h3>
                    <p><span>${appointment.amount} ₽</span></p>
                    <p>Категория: <span>${appointment.service}</span></p>
                    <p><span>${appointment.dateFormatted}</span></p>
                </div>
            </c:forEach>
        </div>

    </div>
    <div id="createExpenseModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeCreateExpenseModal()">&times;</span>
            <h2 id="modalTitle">Новая покупка в категории </h2>
            <form id="createExpenseForm">
                <input type="hidden" id="user_id" name="user_id" required value="${client.id}">
                <label for="amount">Сумма:</label>
                <input type="number" id="amount" name="amount" required>

                <label for="source">Покупка:</label>
                <input type="text" id="source" name="source" maxlength="50" required>

                <input id="category_id" type="text" name="category_id" hidden>
                <button type="submit">Создать</button>
                <div id="message"></div>
            </form>
        </div>
    </div>
    <div id="editExpenseModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeEditExpenseModal()">&times;</span>
            <h2>Редактировать запись о покупке</h2>
            <form id="editExpenseForm">
                <input type="hidden" id="edit-client-id" name="user_id" required value="${client.id}">
                <input type="hidden" id="edit-appointment-id" name="user_id" required>
                <label for="amount">Сумма:</label>
                <input type="number" id="edit-amount" name="amount" required value="">

                <label for="source">Покупка:</label>
                <input type="text" id="edit-source" name="source" maxlength="50" required>

                <label for="category_id">Категория:</label>
                <select id="edit-service-id" name="category_id">
                    <option value="0">Выберите категорию</option>
                    <c:forEach items="${expense_categories}" var="service">
                        <option value="${service.key.id}">${service.key.name}</option>
                    </c:forEach>
                </select>
                <button type="submit">Сохранить</button>
                <div id="edit-message"></div>
            </form>
        </div>
    </div>
    <script src="/scripts/userCabinet.js"></script>
</body>
</html>
