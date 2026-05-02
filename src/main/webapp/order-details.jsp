<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.webmarket.beans.OrderItem" %>

<jsp:include page="/WEB-INF/header.jsp" />

<h1>Детали заказа #<%= request.getAttribute("orderId") %></h1>

<%
    List<OrderItem> items = (List<OrderItem>) request.getAttribute("orderItems");
%>

<% if (items == null || items.isEmpty()) { %>

    <p>В заказе нет товаров</p>

<% } else { %>

    <ul>
        <% for (OrderItem i : items) { %>
            <li>
                Product ID: <%= i.getProductId() %>,
                Qty: <%= i.getQuantity() %>
            </li>
        <% } %>
    </ul>

<% } %>

<jsp:include page="/WEB-INF/footer.jsp" />