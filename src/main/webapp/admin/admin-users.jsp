<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.webmarket.beans.User" %>


<jsp:include page="/WEB-INF/header.jsp" />

<h1>Панель управления - Пользователи</h1>

<%
    List<User> users = (List<User>) request.getAttribute("users");
%>

<% if ("selfdelete".equals(request.getParameter("error"))) { %>
    <p style="color:red;">Вы не можете удалить себя</p>
<% } %>

<% if (users == null || users.isEmpty()) { %>
    <p>Пользователи не найдены</p>

<% } else { %>
    <% for (User u : users) { %>
        <div class="card">
            <p>ID: <%= u.getId() %></p>
            <p>Username: <%= u.getUsername() %></p>
            <p>Email: <%= u.getEmail() %></p>
            <p>Role: <%= u.getRole() %></p>

            <form action="${pageContext.request.contextPath}/admin/users" method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="userId" value="<%= u.getId() %>">
                <button type="submit" style="background:red;">
                    Delete
                </button>
            </form>
        </div>
    <% } %>
<% } %>

<jsp:include page="/WEB-INF/footer.jsp" />