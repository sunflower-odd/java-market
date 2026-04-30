package com.webmarket.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // хэширование пароля
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // проверка пароля
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}