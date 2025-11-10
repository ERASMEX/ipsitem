package com.iph.controller;

import com.iph.dao.UserDAO;
import com.iph.model.User;
import com.iph.view.LoginView;
import com.iph.view.DashboardView;
import javax.swing.JOptionPane;

public class LoginController {

    private final UserDAO userDAO = new UserDAO();
    private LoginView loginView;

    public void iniciar() {
        loginView = new LoginView(this);
        loginView.setVisible(true);
    }

    public void authenticate(String username, String password) {
        // CORRECCIÓN: Llama al método login del DAO que acabamos de definir
        User user = userDAO.login(username, password);

        if (user != null) {
            loginView.dispose();

            // CORRECCIÓN: Instancia DashboardController pasando el username
            DashboardController dashboardController = new DashboardController(user.getUsername());
            new DashboardView(dashboardController).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(loginView, "Usuario o contraseña incorrectos.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void abrirRegistro() {
        JOptionPane.showMessageDialog(loginView, "Abrir Registro (vista pendiente)");
    }
    package com.iph.controller;

import com.iph.dao.UserDAO;
import com.iph.model.User;
import com.iph.view.LoginView;
import com.iph.view.DashboardView;
import javax.swing.JOptionPane;

    public class LoginController {

        private final UserDAO userDAO = new UserDAO();
        private LoginView loginView;

        public void iniciar() {
            loginView = new LoginView(this);
            loginView.setVisible(true);
        }

        public void authenticate(String username, String password) {
            User user = userDAO.login(username, password);

            if (user != null) {
                loginView.dispose();

                // Asegura que DashboardController toma un String (username) como argumento
                DashboardController dashboardController = new DashboardController(user.getUsername());
                new DashboardView(dashboardController).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(loginView, "Usuario o contraseña incorrectos.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
            }
        }

        public void abrirRegistro() {
            JOptionPane.showMessageDialog(loginView, "Abrir Registro (vista pendiente)");
        }
    }
}