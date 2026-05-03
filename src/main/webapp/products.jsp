<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.webmarket.beans.Product" %>
<%@ page import="com.webmarket.beans.Category" %> <%-- ❗ NEW --%>

<jsp:include page="/WEB-INF/header.jsp" />

<h1>Продукты</h1>

<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    String selectedCategory = request.getParameter("categoryId");
%>

<!--фильтрация -->
<form method="get" action="${pageContext.request.contextPath}/products">
    <select name="categoryId">
        <option value="">Все категории</option>
        <% if (categories != null) { %>
            <% for (Category c : categories) { %>
                <option value="<%= c.getId() %>"
                    <%= (selectedCategory != null && selectedCategory.equals(String.valueOf(c.getId())))
                        ? "selected" : "" %> >
                    <%= c.getName() %>
                </option>
            <% } %>
        <% } %>
    </select>
    <button type="submit">Фильтр</button>
</form>

<hr>

<%
    List<Product> products = (List<Product>) request.getAttribute("products");
%>

<% if (products == null || products.isEmpty()) { %>
    <p>Продукты не найдены</p>
<% } else { %>

    <% for (Product p : products) { %>
        <div class="card">
            <h3><%= p.getName() %></h3>
            <p><%= p.getDescription() %></p>
            <p><b>Цена:</b> <%= p.getPrice() %></p>
            <p><b>Категория:</b> <%= p.getCategoryName() %></p>

            <!-- добавить в корзину -->
            <form action="cart" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="productId" value="<%= p.getId() %>">

                Количество:
                <input type="number" name="quantity" value="1" min="1">

                <button type="submit">Add to cart</button>
            </form>
        </div>
    <% } %>

<% } %>

<jsp:include page="/WEB-INF/footer.jsp" />