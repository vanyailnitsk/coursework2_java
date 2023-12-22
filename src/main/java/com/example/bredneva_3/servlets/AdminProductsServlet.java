package com.example.bredneva_3.servlets;

import com.example.bredneva_3.model.Product;
import com.example.bredneva_3.service.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products-manage")
public class AdminProductsServlet extends HttpServlet {
    private final ProductRepository productRepository = new ProductRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productRepository.getAllProducts();
        req.setAttribute("products",products);
        req.getRequestDispatcher("/product-manage.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));
        Product product = new Product(0,name, description,price);
        productRepository.addProduct(product);
        req.setAttribute("message", "Товар успешно создан!");
        resp.sendRedirect("/products-manage");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("id"));
        productRepository.deleteProduct(productId);
        resp.sendRedirect("/products-manage");
    }
}
