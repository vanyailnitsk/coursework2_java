package com.example.bredneva_3.servlets;

import com.example.bredneva_3.model.Appointment;
import com.example.bredneva_3.model.Service;
import com.example.bredneva_3.service.AppointmentService;
import com.example.bredneva_3.service.ServiceRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/appointments")
public class AppointmentServlet extends HttpServlet {
    private final AppointmentService appointmentService = new AppointmentService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int clientId = (Integer) req.getSession().getAttribute("user_id");
        List<Appointment> appointments = appointmentService.getAppointmentsByClientId(clientId);
        req.setAttribute("appointments",appointments);
        req.getRequestDispatcher("/appointments.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String problem = req.getParameter("problem");
        int clientId = (Integer) req.getSession().getAttribute("user_id");
        Appointment appointment = new Appointment(0,null,clientId,problem);
        if (appointmentService.addAppointment(appointment)) {
            List<Appointment> appointments = appointmentService.getAppointmentsByClientId(clientId);
            req.setAttribute("appointments",appointments);
            req.setAttribute("message", "Обращение успешно отправлено!");
            resp.sendRedirect("/appointments");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Ошибка при создании ");
        }

    }
//
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int appointmentId = Integer.parseInt(req.getParameter("id"));
        if (!appointmentService.deleteAppointment(appointmentId)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
