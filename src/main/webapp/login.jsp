<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/header.jsp" />


<link rel="stylesheet" href="css/style.css">

<h1>Вход</h1>

<%
    String error = request.getParameter("error");
    if ("1".equals(error)) {
%>
    <p style="color:red;">Неверный пользователь или пароль</p>
<%
    }
%>

<form action="auth" method="post">

    <input type="hidden" name="action" value="login">

    <label>Имя пользователя:</label><br>
    <input type="text" name="username" required><br>

    <label>Пароль:</label><br>
    <input type="password" name="password" required><br>

    <button type="submit">Login</button>
</form>

<p>
    Нет личного кабинета?
    <a href="register.jsp">Регистрация</a>
</p>

<jsp:include page="/WEB-INF/footer.jsp" />