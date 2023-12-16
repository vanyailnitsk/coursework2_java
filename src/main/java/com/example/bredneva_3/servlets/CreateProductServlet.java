package com.example.bredneva_3.servlets;

import com.example.bredneva_3.model.Product;
import com.example.bredneva_3.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-product")
public class CreateProductServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/create_product.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));
        Product product = new Product(0,name, description,price);
        productService.addProduct(product);
        req.setAttribute("message", "Товар успешно создан!");
        req.getRequestDispatcher("/create_product.jsp").forward(req,resp);
    }
}
