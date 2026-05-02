<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.webmarket.beans.Order" %>


<jsp:include page="/WEB-INF/header.jsp" />

<h1>Ваши заказы</h1>

<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");
%>

<% if (orders == null || orders.isEmpty()) { %>

    <p>У вас пока нет заказов</p>

<% } else { %>

    <% for (Order o : orders) { %>

        <div class="card">

            <h3>Order #<%= o.getId() %></h3>

            <p><b>Date:</b> <%= o.getCreatedAt() %></p>

            <p><b>User ID:</b> <%= o.getUserId() %></p>

            <!-- посмотреть детали заказа -->
            <form action="order" method="get">

                <input type="hidden" name="orderId" value="<%= o.getId() %>">
                <input type="hidden" name="action" value="details">

                <button type="submit">Посмотреть детали</button>

            </form>

        </div>

    <% } %>

<% } %>

<jsp:include page="/WEB-INF/footer.jsp" />