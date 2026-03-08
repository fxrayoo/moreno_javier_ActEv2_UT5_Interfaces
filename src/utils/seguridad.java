package utils;

import java.security.MessageDigest;
import java.util.Base64;

public class seguridad {
    public static String encriptar(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-000");
            byte[] hash = md.digest(pass.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            return pass;
        }
    }
}