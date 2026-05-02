<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/header.jsp" />


<link rel="stylesheet" href="css/style.css">

<h1>Register</h1>

<%
    String error = request.getParameter("error");
    if ("exists".equals(error)) {
%>
    <p style="color:red;">User already exists</p>
<%
    }
%>

<form action="auth" method="post">

    <input type="hidden" name="action" value="register">

    <label>Имя пользователя:</label><br>
    <input type="text" name="username" required><br>

    <label>Пароль:</label><br>
    <input type="password" name="password" required><br>

    <label>Email:</label><br>
    <input type="email" name="email" required><br>

    <button type="submit">Register</button>
</form>

<p>
    Уже есть личный кабинет?
    <a href="login.jsp">Вход</a>
</p>

<jsp:include page="/WEB-INF/footer.jsp" />