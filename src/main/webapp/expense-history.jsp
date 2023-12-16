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
            <h2>Последние покупки</h2>
            <table>
                <thead>
                <tr>
                    <th>Дата</th>
                    <th>Сумма</th>
                    <th>Описание</th>
                    <th>Категория</th>
                    <th>Действие</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${expens}" var="appointment">
                    <tr>
                        <td>${appointment.dateFormatted}</td>
                        <td>${appointment.amount}</td>
                        <td>${appointment.source}</td>
                        <td>${appointment.service}</td>
                        <td>
                            <button class="edit-button"
                                    onclick="openEditExpenseModal(${appointment.id},${appointment.amount},'${appointment.source}')"
                            >Изменить
                            </button>
                            <button class="delete-button" type="submit" onclick="deleteExpense(${appointment.id})">Удалить
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div id="createExpenseModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeCreateExpenseModal()">&times;</span>
            <h2>Добавить запись о покупке</h2>
            <form id="createExpenseForm">
                <input type="hidden" id="user_id" name="user_id" required value="${client.id}">
                <label for="amount">Сумма:</label>
                <input type="number" id="amount" name="amount" required>

                <label for="source">Покупка:</label>
                <input type="text" id="source" name="source" maxlength="50" required>

                <label for="category_id">Категория:</label>
                <select id=category_id name="category_id">
                    <option value="0">Выберите категорию</option>
                    <c:forEach items="${expense_categories}" var="service">
                        <option value="${service.id}">${service.name}</option>
                    </c:forEach>
                </select>
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
                <input type="hidden" id="edit-appointment-id" name="user_id" required>
                <label for="amount">Сумма:</label>
                <input type="number" id="edit-amount" name="amount" required value="">

                <label for="source">Покупка:</label>
                <input type="text" id="edit-source" name="source" maxlength="50" required>

                <label for="category_id">Категория:</label>
                <select id="edit-service-id" name="category_id">
                    <option value="0">Выберите категорию</option>
                    <c:forEach items="${expense_categories}" var="service">
                        <option value="${service.id}">${service.name}</option>
                    </c:forEach>
                </select>
                <button type="submit">Сохранить</button>
                <div id="edit-message"></div>
            </form>
        </div>
    </div>
    <script src="/scripts/appointment.js"></script>
</body>
</html>
