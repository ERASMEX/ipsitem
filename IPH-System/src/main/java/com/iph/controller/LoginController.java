// --- [File: src/main/java/com/iph/controller/LoginController.java] ---
package com.iph.controller;

import com.iph.dao.UserDAO;
import com.iph.view.LoginView;

import javax.swing.*;

public class LoginController {
    private final LoginView view;
    private final UserDAO userDAO;

    public LoginController() {
        // CORRECCIÓN: Se usará UserDAO que es compatible con el schema
        this.userDAO = new UserDAO();
        this.view = new LoginView(this);
    }

    public void iniciar() {
        view.setVisible(true);
    }

    public void intentarLogin(String usuario, String password) {
        if (usuario.trim().isEmpty() || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Usuario y contraseña son requeridos", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // CORRECCIÓN: Se usa UserDAO.login (que ha sido corregido)
        if (userDAO.login(usuario, password)) {
            JOptionPane.showMessageDialog(view, "Login exitoso");
            view.dispose();
            new DashboardController(usuario).iniciar();
        } else {
            JOptionPane.showMessageDialog(view, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}