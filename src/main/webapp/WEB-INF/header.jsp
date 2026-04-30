<%@ page import="com.webmarket.beans.User" %>

<link rel="stylesheet" href="css/style.css">

<div class="nav">

    <a href="products">Products</a>
    <a href="cart">Cart</a>
    <a href="order">Orders</a>

    <span style="float:right;">

    <%
        User user = (User) session.getAttribute("user");
        if (user != null) {
    %>
        Welcome, <b><%= user.getUsername() %></b>
        |
        <a href="auth?action=logout">Logout</a>
    <%
        } else {
    %>
        <a href="login.jsp">Login</a>
        |
        <a href="register.jsp">Register</a>
    <%
        }
    %>

    </span>

</div>

<hr>