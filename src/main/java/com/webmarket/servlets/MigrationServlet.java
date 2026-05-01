package com.webmarket.servlets;

import com.webmarket.util.DBBootstrap;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/migrate")
public class MigrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        DBBootstrap.init();

        resp.getWriter().write("Migrations executed");
    }
}