package com.example.kursovaya.model;

public class Service {
    private int service_id;
    private int client_id;
    private String description;
    private String status;

    public Service(int service_id, int client_id, String description, String status) {
        this.service_id = service_id;
        this.client_id = client_id;
        this.description = description;
        this.status = status;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
