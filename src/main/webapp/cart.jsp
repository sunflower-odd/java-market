<%@ page import="java.util.List" %>
<%@ page import="com.webmarket.beans.Cart" %>
<%@ page import="com.webmarket.beans.Product" %>

<jsp:include page="/WEB-INF/header.jsp" />

<h1>Your Cart</h1>

<%
    List<Cart> cartItems = (List<Cart>) request.getAttribute("cartItems");
    List<Product> products = (List<Product>) request.getAttribute("products");
%>

<% if (cartItems == null || cartItems.isEmpty()) { %>

    <p>Cart is empty</p>

<% } else { %>
    <% for (Cart item : cartItems) {
        Product product = null;
        for (Product p : products) {
            if (p.getId() == item.getProductId()) {
                product = p;
                break;
            }
        }
    %>

    <div class="card">
        <h3>
            <%= (product != null ? product.getName() : "Unknown product") %>
        </h3>

        <p>
            Price: <%= (product != null ? product.getPrice() : 0) %>
        </p>

        <p>
            Quantity: <%= item.getQuantity() %>
        </p>

        <!-- удалить из корзины -->
        <form action="cart" method="post">

            <input type="hidden" name="action" value="remove">
            <input type="hidden" name="cartId" value="<%= item.getId() %>">

            <button type="submit">Remove</button>

        </form>

    </div>

    <% } %>

    <!-- оформить заказ -->
    <form action="order" method="post">
        <input type="hidden" name="action" value="create">
        <button type="submit">Checkout</button>
    </form>

<% } %>

<jsp:include page="/WEB-INF/footer.jsp" />