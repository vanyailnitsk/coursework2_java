package com.example.kursovaya.servlets;

import com.example.kursovaya.model.Appointment;
import com.example.kursovaya.model.User;
import com.example.kursovaya.service.ServiceRepository;
import com.example.kursovaya.service.AppointmentService;
import com.example.kursovaya.service.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class UserServlet extends HttpServlet {
    private final UserRepository userRepository = new UserRepository();
    private final ServiceRepository serviceRepository = new ServiceRepository();
    private final AppointmentService appointmentService = new AppointmentService();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) req.getSession().getAttribute("user_id");
        User user = userRepository.getUserById(userId);
        List<Appointment> expens = appointmentService.getExpensesByUserId(userId);
        req.setAttribute("expense_categories", appointmentService.getUserExpensesByCategories(userId));
        req.setAttribute("user", user);
        req.setAttribute("expenses", expens);
        req.getRequestDispatcher("/userCabinet.jsp").forward(req, resp);
    }
}
