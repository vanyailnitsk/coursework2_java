package com.example.kursovaya.servlets;

import com.example.kursovaya.model.Service;
import com.example.kursovaya.service.AppointmentService;
import com.example.kursovaya.service.IncomeRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/analytics")
public class AnalyticsServlet extends HttpServlet {
    private final AppointmentService appointmentService;
    private final IncomeRepository incomeRepository;

    public AnalyticsServlet() {
        this.appointmentService = new AppointmentService();
        this.incomeRepository = new IncomeRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) req.getSession().getAttribute("user_id");
        Map<Service,Integer> expenseMap = appointmentService.getUserExpensesByCategories(userId);
        Map<Service,Integer> incomeMap = incomeRepository.getUserIncomesByCategories(userId);
        req.setAttribute("expense_categories",expenseMap);
        req.setAttribute("income_categories",incomeMap);
        req.getRequestDispatcher("/analytics.jsp").forward(req,resp);
    }
}
