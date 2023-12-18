package com.example.bredneva_3.servlets;

import com.example.bredneva_3.model.Service;
import com.example.bredneva_3.service.ServiceRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-service")
public class CreateServiceServlet extends HttpServlet {
    private final ServiceRepository serviceRepository = new ServiceRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/create_service.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int clientId = Integer.parseInt(req.getParameter("client_id"));
        String description = req.getParameter("description");
        String status = req.getParameter("status");
        int amount = Integer.parseInt(req.getParameter("amount"));
        Service service = new Service(0,clientId,description,status,amount);
        serviceRepository.addService(service);
        resp.sendRedirect("/services");
    }
}
