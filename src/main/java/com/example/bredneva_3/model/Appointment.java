package com.example.bredneva_3.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Appointment {
    private int appointmentId;
    private Timestamp visitDate;
    private int clientId;
    private String problem;

    public Appointment(int id, Timestamp visitDate, int clientId, String problem) {
        this.appointmentId = id;
        this.visitDate = visitDate;
        this.clientId = clientId;
        this.problem = problem;
    }

    public int getId() {
        return appointmentId;
    }

    public void setId(int id) {
        this.appointmentId = id;
    }

    public Timestamp getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getDateFormatted() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(visitDate);
    }

}
