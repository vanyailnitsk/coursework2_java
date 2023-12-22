package com.example.bredneva_3.servlets;

import com.example.bredneva_3.model.Client;
import com.example.bredneva_3.model.Service;
import com.example.bredneva_3.service.ClientRepository;
import com.example.bredneva_3.service.ServiceRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/lk")
public class LKServlet extends HttpServlet {
    private final ServiceRepository serviceRepository = new ServiceRepository();
    private final ClientRepository clientRepository = new ClientRepository();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int clientId = (Integer) req.getSession().getAttribute("user_id");
        Client client = clientRepository.getClientById(clientId);
        List<Service> services = serviceRepository.getAllServiceByClient(clientId);
        req.setAttribute("services",services);
        req.setAttribute("client",client);
        req.getRequestDispatcher("/dashboard.jsp").forward(req,resp);
    }

//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        Appointment appointment =  new Gson().fromJson(requestBody, Appointment.class);
//        if (appointmentService.addAppointment(appointment)) {
//            resp.getWriter().println("Success");
//        }
//        else {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            JsonObject error = new JsonObject();
//            resp.setContentType("application/json");
//            resp.getWriter().write(error.toString());
//        }
//    }
//
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)  {
//        int expenseId = Integer.parseInt(req.getParameter("id"));
//        if (!appointmentService.deleteExpense(expenseId)) {
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//        Appointment appointment =  new Gson().fromJson(requestBody, Appointment.class);
//        if (appointmentService.editAppointment(appointment)) {
//            resp.getWriter().println("Success");
//        }
//        else {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            JsonObject error = new JsonObject();
//            error.addProperty("message","No appointment with id "+ appointment.getId());
//            resp.setContentType("application/json");
//            resp.getWriter().write(error.toString());
//        }
//    }
}
