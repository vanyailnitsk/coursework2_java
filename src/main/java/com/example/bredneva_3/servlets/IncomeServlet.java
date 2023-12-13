package com.example.bredneva_3.servlets;

import com.example.bredneva_3.model.Product;
import com.example.bredneva_3.service.IncomeCategoryRepository;
import com.example.bredneva_3.service.ProductService;
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

@WebServlet("/income")
public class IncomeServlet extends HttpServlet {
    private final ProductService productService;
    private final IncomeCategoryRepository incomeCategoryRepository;

    public IncomeServlet() {
        this.productService = new ProductService();
        this.incomeCategoryRepository = new IncomeCategoryRepository();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) req.getSession().getAttribute("user_id");
        List<Product> products = productService.getIncomesByUserId(userId);
        req.setAttribute("incomes", products);
        req.setAttribute("userId",userId);
        req.setAttribute("income_categories",incomeCategoryRepository.getAllCategories());
        req.getRequestDispatcher("/income-history.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Product product =  new Gson().fromJson(requestBody, Product.class);
        if (productService.addIncome(product)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            error.addProperty("message","No user with id "+ product.getUserId());
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int incomeId = Integer.parseInt(req.getParameter("id"));
        if (!productService.deleteIncome(incomeId)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Product product =  new Gson().fromJson(requestBody, Product.class);
        if (productService.editIncome(product)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            error.addProperty("message","No product with id "+ product.getId());
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }
}
