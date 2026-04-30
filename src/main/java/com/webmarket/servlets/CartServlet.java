package com.webmarket.servlets;

import com.webmarket.beans.Cart;
import com.webmarket.beans.User;
import com.webmarket.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import com.webmarket.beans.Product;
import com.webmarket.service.ProductService;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private CartService cartService = new CartService();
    private ProductService productService = new ProductService();

    // показать корзину
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        // если не авторизован
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Cart> cartItems = cartService.getUserCart(user.getId());
        List<Product> products = productService.getAllProducts();
        request.setAttribute("cartItems", cartItems);
        request.setAttribute("products", products);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // добавить товар в корзину
        if ("add".equals(action)) {

            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            cartService.addToCart(user.getId(), productId, quantity);

            response.sendRedirect("cart");
        }

        // удалить товар из корзины
        else if ("remove".equals(action)) {

            int cartId = Integer.parseInt(request.getParameter("cartId"));

            cartService.removeItem(cartId);

            response.sendRedirect("cart");
        }

        // очистить корзину
        else if ("clear".equals(action)) {

            cartService.clearCart(user.getId());

            response.sendRedirect("cart");
        }
    }
}