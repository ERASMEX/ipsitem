// --- [File: src/main/java/com/iph/view/LoginView.java] ---
package com.iph.view;

import com.iph.config.AppConfig;
import com.iph.controller.LoginController;
import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private LoginController controller;

    public LoginView(LoginController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        // CORRECCIÓN: Título movido desde Main.java
        setTitle(AppConfig.get("app.name") + " v" + AppConfig.get("app.version"));
        setSize(400, 250); // Tamaño ajustado
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField(20);
        JLabel lblPassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField(20);
        JButton btnLogin = new JButton("Ingresar");

        // Acción para el botón
        btnLogin.addActionListener(e -> login());
        // Acción para la tecla Enter en el campo de contraseña
        txtPassword.addActionListener(e -> login());

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST; panel.add(lblUsuario, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL; panel.add(txtUsuario, gbc);
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST; panel.add(lblPassword, gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL; panel.add(txtPassword, gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST; gbc.fill = GridBagConstraints.NONE; panel.add(btnLogin, gbc);

        add(panel, BorderLayout.CENTER);
    }

    private void login() {
        controller.intentarLogin(
                txtUsuario.getText(),
                new String(txtPassword.getPassword())
        );
    }
}