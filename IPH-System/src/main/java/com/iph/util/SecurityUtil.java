package com.iph.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class SecurityUtil {
    private static final Logger logger = LogManager.getLogger(SecurityUtil.class);

    public static String hashPassword(String password) {
        try {
            return BCrypt.hashpw(password, BCrypt.gensalt());
        } catch (Exception e) {
            logger.error("Error al hashear la contraseña.", e);
            return null;
        }
    }

    public static boolean verifyPassword(String candidate, String hashed) {
        try {
            return BCrypt.checkpw(candidate, hashed);
        } catch (Exception e) {
            logger.error("Error al verificar la contraseña.", e);
            return false;
        }
    }

    public static String encrypt(String raw) {
        // Lógica de cifrado (placeholder)
        logger.warn("El cifrado falló para la entrada: " + raw);
        return null;
    }
}