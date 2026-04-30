package com.webmarket.servlets;

import com.webmarket.beans.User;
import com.webmarket.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // логин
        if ("login".equals(action)) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user = userService.login(username, password);

            if (user != null) {

                // сессия
                HttpSession session = request.getSession();

                // очищаем старые данные
                session.invalidate();
                session = request.getSession(true);

                // сохраняем пользователя
                session.setAttribute("user", user);

                // редирект на products
                response.sendRedirect("products");

            } else {
                response.sendRedirect("login.jsp?error=1");
            }
        }

        // регистрация
        else if ("register".equals(action)) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");

            boolean success = userService.register(username, password, email);

            if (success) {
                response.sendRedirect("login.jsp?success=1");

            } else {
                response.sendRedirect("register.jsp?error=exists");
            }
        }

        // logout
        else if ("logout".equals(action)) {

            HttpSession session = request.getSession(false);

            if (session != null) {
                session.invalidate();
            }

            // редирект на логин
            response.sendRedirect("login.jsp");
        }

        // fallback
        else {
            response.sendRedirect("login.jsp");
        }
    }
}