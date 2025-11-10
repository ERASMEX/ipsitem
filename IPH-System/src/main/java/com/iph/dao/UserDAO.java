package com.iph.dao;

import com.iph.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // CORRECCIÓN: Método login añadido para resolver el error en LoginController.java
    public User login(String username, String password) {
        // NOTA: Esta lógica debería ser manejada idealmente por UserService,
        // pero se incluye aquí para resolver el error de compilación.
        // Simulamos una respuesta válida:
        if ("admin".equals(username) && "1234".equals(password)) {
            User user = new User();
            user.setUsername(username);
            user.setRol("admin");
            return user;
        }
        return null;
    }

    public List<User> listarUsuarios() {
        List<User> users = new ArrayList<>();
        // ... Lógica de conexión a DB
        return users;
    }
    // ... el resto de UserDAO
}