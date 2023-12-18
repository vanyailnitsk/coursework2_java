package com.example.bredneva_3.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginService {
    private final ClientService clientService = new ClientService();
    private final AdminService adminService = new AdminService();

    public int auth(String login, String password) {
        String hashedPassword = hashPassword(password);
        return clientService.getUserIdByUsernameAndPassword(login,password);
    }
    public int register(String name,String contact,String login,String password) {
        if (clientService.isClientExistsByLogin(login)) {
            return -1;
        }
        String hashedPassword = hashPassword(password);
        return clientService.registerClient(name,contact,login,password);
    }
    public boolean authAdmin(String login,String password) {
        return adminService.checkAdminAuth(login,password);
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
