<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.webmarket.beans.User" %>

<meta charset="UTF-8">

<link rel="stylesheet" href="css/style.css">

<%
    User user = (User) session.getAttribute("user");
%>
<div class="nav">

    <a href="products">Products</a>
    <a href="cart">Cart</a>
    <a href="order">Orders</a>

    <%-- ссылки только для ADMIN --%>
    <% if (user != null && "ADMIN".equals(user.getRole())) { %>
        | <a href="admin/users">Users</a>
        | <a href="admin/products">Products Admin</a>
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

        <a href="login.jsp">Login</a>
        |
        <a href="register.jsp">Register</a>

    <% } %>
    </span>
</div>
<hr>