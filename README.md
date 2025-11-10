// --- [File: src/main/java/com/iph/Main.java] ---
package com.iph;

import com.iph.config.AppConfig;
import com.iph.controller.LoginController;
import com.formdev.flatlaf.FlatLightLaf;
import com.iph.config.DatabaseConfig;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatLightLaf.setup();
            // Inicializa la base de datos al arrancar
            DatabaseConfig.initDatabase();
            new LoginController().iniciar();
        });
    }
}
