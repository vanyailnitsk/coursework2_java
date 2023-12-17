<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Обращения</title>
    <link rel="stylesheet" type="text/css" href="/styles/appointments.css">
</head>
<body>
    <jsp:include page="main.html"/>
    <h2>Ваши заявки</h2>
    <table>
        <tr>
            <th>Проблема</th>
            <th>Дата</th>
            <th></th>
        </tr>
        <c:forEach items="${appointments}" var="appointment">
            <tr>
                <td>${appointment.problem}</td>
                <td>${appointment.dateFormatted}</td>
                <td>
                    <form id="delete">
                        <button type="button" onclick="submitDelete(${appointment.appointmentId})">Удалить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <script>
        function submitDelete(appointmentId) {
            event.preventDefault();
            var form = document.getElementById('delete');
            fetch('/appointments?id='+appointmentId, {
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

