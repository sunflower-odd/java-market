<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.webmarket.beans.User" %>

<meta charset="UTF-8">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

<%
    User user = (User) session.getAttribute("user");
%>
<div class="nav">

    <a href="${pageContext.request.contextPath}/products">Продукты</a>
    <a href="${pageContext.request.contextPath}/cart">Корзина</a>
    <a href="${pageContext.request.contextPath}/order">Заказы</a>

    <%-- ссылки только для ADMIN --%>
    <% if (user != null && "ADMIN".equals(user.getRole())) { %>
        | <a href="${pageContext.request.contextPath}/admin/users">Управление пользователями</a>
        | <a href="${pageContext.request.contextPath}/admin/products">Управление продуктами</a>
    <% } %>

    <span style="float:right;">

    <% if (user != null) { %>

        Welcome, <b><%= user.getUsername() %></b>
        |
        <form action="auth" method="post" style="display:inline;">
            <input type="hidden" name="action" value="logout">
            <button type="submit" style="background:none;border:none;color:blue;cursor:pointer;padding:0;">
                Logout
            </button>
        </form>
    <% } else { %>

        <a href="login.jsp">Логин</a>
        |
        <a href="register.jsp">Регистрация</a>

    <% } %>
    </span>
</div>
<hr>