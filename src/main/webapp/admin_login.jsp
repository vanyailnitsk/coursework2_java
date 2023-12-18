<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Вход администратора</title>
  <link rel="stylesheet" href="/styles/login.css">
</head>
<body>
  <div class="login-container">
    <h2>Вход в панель администратора</h2>
    <form action="/admin-login" method="post">
      <label for="login">Логин:</label>
      <input type="text" id="login" name="login" required>
      <br>
      <label for="password">Пароль:</label>
      <input type="password" id="password" name="password" required>
      <br>
      <button type="submit">Войти</button>
    </form>
    <div class="error-message">${errorMessage}</div>
  </div>
</body>
</html>
