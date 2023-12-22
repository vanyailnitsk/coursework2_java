package com.example.bredneva_3.servlets;

import com.example.bredneva_3.model.Service;
import com.example.bredneva_3.service.ServiceRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/services")
public class ServiceServlet extends HttpServlet {
    private final ServiceRepository serviceRepository = new ServiceRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Service> services = serviceRepository.getAllServices();
        req.setAttribute("services",services);
        req.getRequestDispatcher("/admin_services.jsp").forward(req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int serviceId = Integer.parseInt(req.getParameter("id"));
        serviceRepository.deleteService(serviceId);
    }
}
