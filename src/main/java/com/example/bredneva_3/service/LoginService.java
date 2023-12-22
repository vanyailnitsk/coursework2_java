package com.example.bredneva_3.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginService {
    private final ClientRepository clientRepository = new ClientRepository();
    private final AdminRepository adminRepository = new AdminRepository();

    public int auth(String login, String password) {
        return clientRepository.getUserIdByUsernameAndPassword(login,password);
    }
    public int register(String name,String contact,String login,String password) {
        if (clientRepository.isClientExistsByLogin(login)) {
            return -1;
        }
        return clientRepository.registerClient(name,contact,login,password);
    }
    public boolean authAdmin(String login,String password) {
        return adminRepository.checkAdminAuth(login,password);
    }
}
