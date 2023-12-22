package com.example.bredneva_3.servlets;

import com.example.bredneva_3.model.Appointment;
import com.example.bredneva_3.model.Client;
import com.example.bredneva_3.service.ServiceRepository;
import com.example.bredneva_3.service.AppointmentRepository;
import com.example.bredneva_3.service.ClientRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class UserServlet extends HttpServlet {
    private final ClientRepository clientRepository = new ClientRepository();
    private final ServiceRepository serviceRepository = new ServiceRepository();
    private final AppointmentRepository appointmentRepository = new AppointmentRepository();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) req.getSession().getAttribute("user_id");
        Client client = clientRepository.getClientById(userId);
        List<Appointment> appointments = appointmentRepository.getAppointmentsByClientId(userId);
        req.setAttribute("expense_categories", appointmentRepository.getAppointmentsByClientId(userId));
        req.setAttribute("user", client);
        req.setAttribute("expenses", appointments);
        req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
    }
}
