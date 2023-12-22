package com.example.bredneva_3.servlets;

import com.example.bredneva_3.model.Client;
import com.example.bredneva_3.model.Service;
import com.example.bredneva_3.service.ClientRepository;
import com.example.bredneva_3.service.ServiceRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/lk")
public class LKServlet extends HttpServlet {
    private final ServiceRepository serviceRepository = new ServiceRepository();
    private final ClientRepository clientRepository = new ClientRepository();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int clientId = (Integer) req.getSession().getAttribute("user_id");
        Client client = clientRepository.getClientById(clientId);
        List<Service> services = serviceRepository.getAllServiceByClient(clientId);
        req.setAttribute("services",services);
        req.setAttribute("client",client);
        req.getRequestDispatcher("/dashboard.jsp").forward(req,resp);
    }

}
