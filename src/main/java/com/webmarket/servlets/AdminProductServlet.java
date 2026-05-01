package com.webmarket.servlets;

import com.webmarket.beans.Product;
import com.webmarket.beans.User;
import com.webmarket.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/admin/products")
public class AdminProductServlet extends HttpServlet {

    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User admin = (session != null) ? (User) session.getAttribute("user") : null;

        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Product> products = productService.getAllProducts();

        request.setAttribute("products", products);
        request.getRequestDispatcher("admin-products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User admin = (session != null) ? (User) session.getAttribute("user") : null;

        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        // создать товар
        if ("create".equals(action)) {

            String name = request.getParameter("name");
            String description = request.getParameter("description");
            BigDecimal price = new BigDecimal(request.getParameter("price"));

            productService.createProduct(name, description, price, 0);

            response.sendRedirect("admin/products");
        }

        // удалить товар
        else if ("delete".equals(action)) {

            int id = Integer.parseInt(request.getParameter("productId"));

            productService.deleteProduct(id);

            response.sendRedirect("admin/products");
        }
    }
}