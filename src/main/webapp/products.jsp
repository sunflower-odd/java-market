<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.webmarket.beans.Product" %>


<jsp:include page="/WEB-INF/header.jsp" />

<h1>Products</h1>

<%
    List<Product> products = (List<Product>) request.getAttribute("products");
%>

<% if (products == null || products.isEmpty()) { %>
    <p>No products found</p>
<% } else { %>

    <% for (Product p : products) { %>
        <div class="card">
            <h3><%= p.getName() %></h3>
            <p><%= p.getDescription() %></p>
            <p><b>Price:</b> <%= p.getPrice() %></p>
            <p><b>Category ID:</b> <%= p.getCategoryId() %></p>
            <!-- добавить в корзину -->
            <form action="cart" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="productId" value="<%= p.getId() %>">
                Quantity:
                <input type="number" name="quantity" value="1" min="1">
                <button type="submit">Add to cart</button>
            </form>
        </div>
    <% } %>
<% } %>

<jsp:include page="/WEB-INF/footer.jsp" />