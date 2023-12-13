package com.example.bredneva_3.servlets;

import com.example.bredneva_3.model.Appointment;
import com.example.bredneva_3.service.ServiceRepository;
import com.example.bredneva_3.service.AppointmentService;
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

@WebServlet("/expense")
public class ExpenseServlet extends HttpServlet {
    private final AppointmentService appointmentService;
    private final ServiceRepository serviceRepository;

    public ExpenseServlet() {
        this.appointmentService = new AppointmentService();
        this.serviceRepository = new ServiceRepository();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) req.getSession().getAttribute("user_id");
        List<Appointment> expens = appointmentService.getExpensesByUserId(userId);
        req.setAttribute("expenses", expens);
        req.setAttribute("expense_categories", serviceRepository.getAllCategories());
        req.getRequestDispatcher("/expense-history.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Appointment appointment =  new Gson().fromJson(requestBody, Appointment.class);
        if (appointmentService.addExpense(appointment)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            error.addProperty("message","No user with id "+ appointment.getUserId());
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)  {
        int expenseId = Integer.parseInt(req.getParameter("id"));
        if (!appointmentService.deleteExpense(expenseId)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Appointment appointment =  new Gson().fromJson(requestBody, Appointment.class);
        if (appointmentService.editExpense(appointment)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            error.addProperty("message","No appointment with id "+ appointment.getId());
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }
}
