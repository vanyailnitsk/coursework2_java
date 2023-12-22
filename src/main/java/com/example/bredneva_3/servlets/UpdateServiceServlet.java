package com.example.bredneva_3.servlets;

import com.example.bredneva_3.model.Service;
import com.example.bredneva_3.service.ServiceRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-service")
public class UpdateServiceServlet extends HttpServlet {
    private ServiceRepository serviceRepository = new ServiceRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int serviceId = Integer.parseInt(req.getParameter("serviceId"));
        Service service = serviceRepository.getById(serviceId);
        req.setAttribute("service",service);
        req.setAttribute("serviceId",serviceId);
        req.getRequestDispatcher("edit_service.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description");
        String status = req.getParameter("status");
        int serviceId = Integer.parseInt(req.getParameter("service_id"));
        Service service = new Service(serviceId,-1,description,status,-1);
        serviceRepository.editService(service);
        resp.sendRedirect("/services");
    }
}
