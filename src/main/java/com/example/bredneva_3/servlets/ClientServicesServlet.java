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

@WebServlet("/my-services")
public class ClientServicesServlet extends HttpServlet {
    private final ServiceRepository serviceRepository = new ServiceRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int clientId = (Integer) req.getSession().getAttribute("user_id");
        List<Service> services = serviceRepository.getAllServiceByClient(clientId);
        req.setAttribute("services",services);
        req.getRequestDispatcher("/my_services.jsp").forward(req,resp);
    }
}
