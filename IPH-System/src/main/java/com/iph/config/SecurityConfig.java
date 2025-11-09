// --- [File: src/main/java/com/iph/config/SecurityConfig.java] ---
package com.iph.config;

import org.mindrot.jbcrypt.BCrypt;

// Usaremos esta clase como la utilidad de seguridad principal para BCrypt
public class SecurityConfig {
    private static final int BCRYPT_ROUNDS = 12;

    public static String hashPassword(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt(BCRYPT_ROUNDS));
    }

    public static boolean checkPassword(String plain, String hashed) {
        if (hashed == null || !hashed.startsWith("$2a$")) {
            // Prevenir error si el hash no es válido
            return false;
        }
        try {
            return BCrypt.checkpw(plain, hashed);
        } catch (Exception e) {
            return false;
        }
    }
}