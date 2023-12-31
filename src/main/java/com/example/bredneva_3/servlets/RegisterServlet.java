package com.example.bredneva_3.servlets;

import com.example.bredneva_3.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final LoginService loginService;

    public RegisterServlet() {
        this.loginService = new LoginService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errorMessage", "");
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
        resp.setContentType("text/html");
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String contact = req.getParameter("contact");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int userId = loginService.register(name,contact,login,password);
        if (userId != -1) {
            req.getSession().setAttribute("user_id", userId);
            resp.sendRedirect( "/lk");
        } else {
            req.setAttribute("errorMessage", "Логин занят!");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            super.doPost(req, resp);
        }
    }
}
