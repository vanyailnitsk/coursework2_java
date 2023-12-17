package com.example.bredneva_3.service;

import com.example.bredneva_3.model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AppointmentService {
    private final DataBaseService dataBaseService;
    static final String SELECT_BY_USERID = "SELECT * from appointments WHERE client_id = ? ORDER BY visit_date desc;";
    static final String SELECT_ALL = "SELECT * from appointments" +
            " JOIN clients on appointments.client_id=clients.client_id ORDER BY visit_date desc";
    static final String INSERT = "INSERT INTO appointments(visit_date,client_id,problem) values (NOW(),?,?);";
    static final String DELETE = "DELETE FROM appointments where appointment_id=?";
    static final String UPDATE = "UPDATE expense SET problem=? where appointment_id=?";
    public AppointmentService() {
        this.dataBaseService = new DataBaseService();
    }
    public List<Appointment> getAppointmentsByClientId(Integer clientId) {
        List<Appointment> appointments = new ArrayList<>();
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_USERID);
            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Appointment appointment = new Appointment(
                        resultSet.getInt("appointment_id"),
                        resultSet.getTimestamp("visit_date"),
                        resultSet.getInt("client_id"),
                        resultSet.getString("problem")
                );
                appointments.add(appointment);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return appointments;
    }
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        try {
            ResultSet resultSet = dataBaseService.select(SELECT_ALL);
            while (resultSet.next()) {
                Appointment appointment = new Appointment(
                        resultSet.getInt("appointment_id"),
                        resultSet.getTimestamp("visit_date"),
                        resultSet.getInt("client_id"),
                        resultSet.getString("problem")
                );
                appointment.setContact(resultSet.getString("contact"));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    public boolean addAppointment(Appointment appointment) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(INSERT);
            statement.setInt(1, appointment.getClientId());
            statement.setString(2, appointment.getProblem());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean editAppointment(Appointment appointment) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setString(1, appointment.getProblem());
            statement.setInt(2, appointment.getAppointmentId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean deleteAppointment(Integer id) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(DELETE);
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
