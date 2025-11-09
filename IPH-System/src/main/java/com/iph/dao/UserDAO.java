package com.iph.dao;

import com.iph.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
// ... otros imports

public class UserDAO {

    // Asumo que este es el método que causaba el error de constructor
    public List<User> listarUsuarios() {
        List<User> users = new ArrayList<>();
        // ... Lógica de conexión a DB
        try (Connection conn = null; // Placeholder
             PreparedStatement stmt = null; // Placeholder
             ResultSet rs = null) { // Placeholder

            while (rs != null && rs.next()) {
                // CORRECCIÓN: Usar constructor sin argumentos y setters
                // Soluciona: constructor User in class com.iph.model.User cannot be applied...
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("usuario"));
                user.setEmail(rs.getString("email"));
                user.setRol(rs.getString("rol"));
                users.add(user);
            }
        } catch (Exception e) {
            // Manejo de excepción
        }
        return users;
    }
    // ... el resto de UserDAO
}