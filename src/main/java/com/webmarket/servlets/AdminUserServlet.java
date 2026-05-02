package com.webmarket.servlets;

import com.webmarket.beans.User;
import com.webmarket.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


@WebServlet("/admin/users")
public class AdminUserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User admin = (User) request.getSession().getAttribute("user");

        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        List<User> users = userService.getAllUsers();

        request.setAttribute("users", users);
        request.getRequestDispatcher("admin-users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User admin = (session != null) ? (User) session.getAttribute("user") : null;

        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String action = request.getParameter("action");

        // удалить пользователя
        if ("delete".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            // админ не может удалить сам себя
            if (userId == admin.getId()) {
                response.sendRedirect(request.getContextPath() + "/admin/users?error=selfdelete");
                return;
            }
            userService.deleteUser(userId);
            response.sendRedirect(request.getContextPath() + "/admin/users");
        }

    }
}