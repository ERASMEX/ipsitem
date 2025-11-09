package com.iph.service;

import com.iph.config.DatabaseConfig;
import com.iph.model.User;
import com.iph.util.SecurityUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private static final Map<String, Integer> failedAttempts = new HashMap<>();
    private static final int MAX_ATTEMPTS = 5;

    public static User authenticate(String username, String password) {
        if (isUserBlocked(username)) {
            LOGGER.warn("Usuario bloqueado por intentos fallidos: " + username);
            return null;
        }

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT id, username, email, rol, password FROM users WHERE username = ? AND activo = TRUE")) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRol(rs.getString("rol"));

                if (SecurityUtil.verifyPassword(password, storedHash)) {
                    resetFailedAttempts(username);
                    updateLastLogin(username);
                    LOGGER.info("Login exitoso: " + username);
                    // Firma corregida a 4 Strings
                    AuditService.log("LOGIN", "users", username, String.format("Login exitoso (ID: %d)", user.getId()));
                    return user;
                } else {
                    registerFailedAttempt(username);
                    LOGGER.warn("Intento de login fallido: " + username);
                }
            } else {
                LOGGER.warn("Usuario no encontrado: " + username);
            }

        } catch (SQLException e) {
            LOGGER.error("Error en autenticación: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error al conectar a la base de datos: " + e.getMessage());
        }

        return null;
    }

    public static boolean registerUser(User user, String password) {
        // ... implementación de registro
        return false;
    }

    private static void updateLastLogin(String username) {
        // ... implementación de update last login
    }

    private static boolean isUserBlocked(String username) {
        // ... implementación de bloqueo
        return false;
    }

    private static void registerFailedAttempt(String username) {
        // ... implementación de registro de intento fallido
    }

    private static void resetFailedAttempts(String username) {
        failedAttempts.remove(username);
    }

    public static boolean changePassword(String username, String oldPassword, String newPassword) {
        // ... implementación de cambio de contraseña
        return false;
    }

    public static boolean isAdmin(String username) {
        // ... implementación de chequeo de rol
        return false;
    }

    public static void unlockUser(String username) {
        failedAttempts.remove(username);
        LOGGER.info("Usuario desbloqueado: " + username);
        // Firma corregida a 4 Strings
        AuditService.log("UNLOCK", "users", "ADMIN", "Usuario desbloqueado: " + username);
    }
}