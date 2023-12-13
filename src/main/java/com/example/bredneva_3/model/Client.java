package com.example.bredneva_3.model;

public class Client {
    private int id;
    private String name;
    private String contact;
    private String login;
    private String password;

    public Client(int id, String name, String contact, String login, String password) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
