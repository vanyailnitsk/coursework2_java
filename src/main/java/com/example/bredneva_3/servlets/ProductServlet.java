package com.example.bredneva_3.servlets;

import com.example.bredneva_3.model.Product;
import com.example.bredneva_3.service.ProductRepository;
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

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private final ProductRepository productRepository;

    public ProductServlet() {
        this.productRepository = new ProductRepository();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Integer userId = (Integer) req.getSession().getAttribute("user_id");
        List<Product> products = productRepository.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/products.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Product product = new Gson().fromJson(requestBody, Product.class);
        if (productRepository.addProduct(product)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int productId = Integer.parseInt(req.getParameter("id"));
        if (!productRepository.deleteProduct(productId)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Product product = new Gson().fromJson(requestBody, Product.class);
        if (productRepository.editProduct(product)) {
            resp.getWriter().println("Success");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonObject error = new JsonObject();
            resp.setContentType("application/json");
            resp.getWriter().write(error.toString());
        }
    }
}
