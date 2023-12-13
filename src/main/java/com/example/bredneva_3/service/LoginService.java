package com.example.bredneva_3.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginService {
    private final ClientService clientService;

    public LoginService() {
        this.clientService = new ClientService();
    }

    public int auth(String login, String password) {
        String hashedPassword = hashPassword(password);
        return clientService.getUserIdByUsernameAndPassword(login,hashedPassword);
    }
    public int register(String name,String login,String password) {
        if (clientService.isClientExistsByLogin(login)) {
            return -1;
        }
        String hashedPassword = hashPassword(password);
        return clientService.registerClient(name,login,hashedPassword);
    }
    public String hashPassword(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        BigInteger hashInt = new BigInteger(1, hashBytes);
        return hashInt.toString(16);
    }
}
