package com.example.bredneva_3.servlets;

import com.example.bredneva_3.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin-login")
public class AdminLoginServlet extends HttpServlet {
    private final AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMessage", "");
        req.getRequestDispatcher("/admin_login.jsp").forward(req, resp);
        resp.setContentType("text/html");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (adminService.checkAdminAuth(login,password)) {
            resp.sendRedirect( "/admin");
        } else {
            req.setAttribute("errorMessage", "Неправильный логин или пароль");
            req.getRequestDispatcher("/admin_login.jsp").forward(req, resp);
            super.doPost(req, resp);
        }
    }
}
