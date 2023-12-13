package com.example.bredneva_3.servlets;

import com.example.bredneva_3.model.Appointment;
import com.example.bredneva_3.model.Client;
import com.example.bredneva_3.service.ServiceRepository;
import com.example.bredneva_3.service.AppointmentService;
import com.example.bredneva_3.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class UserServlet extends HttpServlet {
    private final ClientService clientService = new ClientService();
    private final ServiceRepository serviceRepository = new ServiceRepository();
    private final AppointmentService appointmentService = new AppointmentService();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) req.getSession().getAttribute("user_id");
        Client client = clientService.getUserById(userId);
        List<Appointment> expens = appointmentService.getExpensesByUserId(userId);
        req.setAttribute("expense_categories", appointmentService.getUserExpensesByCategories(userId));
        req.setAttribute("user", client);
        req.setAttribute("expenses", expens);
        req.getRequestDispatcher("/userCabinet.jsp").forward(req, resp);
    }
}
