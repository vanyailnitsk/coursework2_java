package com.example.bredneva_3.servlets;

import com.example.bredneva_3.model.Service;
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

@WebServlet("/expense-category")
public class ExpenseCategoryServlet extends HttpServlet {
    private final ServiceRepository serviceRepository;

    public ExpenseCategoryServlet() {
        this.serviceRepository = new ServiceRepository();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Service> categories = serviceRepository.getAllCategories();
        req.setAttribute("categories",categories);
        req.getRequestDispatcher("/categories.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Service service = new Gson().fromJson(requestBody, Service.class);
        if (serviceRepository.addCategory(service)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error while creating service");
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("id"));
        if (!serviceRepository.deleteCategory(categoryId)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Service service = new Gson().fromJson(requestBody, Service.class);
        if (serviceRepository.editCategory(service)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            error.addProperty("message","No expense with id "+ service.getId());
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }
}
