package com.iph.controller;

import com.iph.model.IPHRecord;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.io.IOException;

public class DashboardController extends Controller {

    private final AdminController adminController = new AdminController();
    private final String loggedInUsername;

    // CORRECCIÓN: Constructor que acepta el username
    // Soluciona: constructor DashboardController cannot be applied to given types
    public DashboardController(String username) {
        this.loggedInUsername = username;
    }

    // Sobreescribe el método base para devolver el usuario real
    @Override
    public String getUsuario() {
        return loggedInUsername;
    }

    // CORRECCIÓN: Método guardarIPH añadido (requerido por RegisterIPHView.java)
    // Soluciona: cannot find symbol method guardarIPH(com.iph.model.IPHRecord,java.lang.String)
    public void guardarIPH(IPHRecord record, String username) {
        // NOTA: Aquí iría la lógica de negocio para guardar en la base de datos
        JOptionPane.showMessageDialog(null,
                "IPH guardado por " + username + " (Lógica de guardado pendiente)");
    }

    // Métodos requeridos por DashboardView.java
    public void importarExcel() {
        JOptionPane.showMessageDialog(null, "Importar Excel (lógica pendiente)");
    }

    public void comprobarActualizacion() {
        JOptionPane.showMessageDialog(null, "Comprobar Actualización (lógica pendiente)");
    }

    public void configurarBotonMapa(JButton mapButton) {
        mapButton.addActionListener(e -> {
            try {
                abrirMapa();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al abrir el mapa: " + ex.getMessage());
            }
        });
    }

    private void abrirMapa() throws IOException {
        System.out.println("Abriendo mapa...");
    }

    public void abrirAdminPanel() {
        adminController.iniciar();
    }
}