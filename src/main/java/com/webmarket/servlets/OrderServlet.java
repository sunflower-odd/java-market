package com.webmarket.servlets;

import com.webmarket.beans.Order;
import com.webmarket.beans.User;
import com.webmarket.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private OrderService orderService = new OrderService();

    // получить историю заказов
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Order> orders = orderService.getUserOrders(user.getId());

        // передаём список заказов в JSP
        request.setAttribute("orders", orders);

        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }

    // оформить заказ

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        // создать заказ
        if ("create".equals(action)) {
            int orderId = orderService.createOrder(user.getId());
            if (orderId > 0) {
                // показываем id заказа на странице подтверждения оформления заказа
                response.sendRedirect("order?success=1&orderId=" + orderId);
            } else {
                response.sendRedirect("cart?error=order_failed");
            }
        }

        // просмотр одного заказа
        else if ("view".equals(action)) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            request.setAttribute("orderItems", orderService.getOrderItems(orderId));
            request.setAttribute("orderId", orderId);
            request.getRequestDispatcher("order-details.jsp").forward(request, response);
        }
    }
}