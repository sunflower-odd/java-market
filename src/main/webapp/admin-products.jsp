<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.webmarket.beans.Product" %>


<jsp:include page="/WEB-INF/header.jsp" />

<h1>Admin Panel - Products</h1>

<h2>Create product</h2>

<form action="admin/products" method="post">

    <input type="hidden" name="action" value="create">

    <input type="text" name="name" placeholder="Name" required><br>
    <input type="text" name="description" placeholder="Description"><br>
    <input type="number" step="0.01" name="price" placeholder="Price" required><br>

    <button type="submit">Create</button>

</form>

<hr>

<%
    List<Product> products = (List<Product>) request.getAttribute("products");
%>

<% if (products != null) { %>

    <% for (Product p : products) { %>

        <div class="card">

            <h3><%= p.getName() %></h3>
            <p><%= p.getDescription() %></p>
            <p>Price: <%= p.getPrice() %></p>

            <form action="admin/products" method="post">

                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="productId" value="<%= p.getId() %>">

                <button type="submit" style="background:red;">
                    Delete
                </button>

            </form>

        </div>

    <% } %>

<% } %>

<jsp:include page="/WEB-INF/footer.jsp" />