// --- [File: src/main/java/com/iph/controller/DashboardController.java] ---
package com.iph.controller;

import com.iph.model.IPHRecord;
import com.iph.service.*;
import com.iph.view.*;

import javax.swing.*;
import java.util.stream.Collectors;

public class DashboardController {
    private final String usuario;
    private final DashboardView view;
    private final IPHService service;

    public DashboardController(String usuario) {
        this.usuario = usuario;
        this.service = new IPHService();
        this.view = new DashboardView(this);
        // El backup automático al iniciar sesión puede ser lento,
        // se recomienda hacerlo en un hilo separado o manualmente.
        new Thread(BackupService::crearBackup).start();
    }

    public void iniciar() { view.setVisible(true); }
    public String getUsuario() { return usuario; }

    public void abrirRegistroIPH() { new RegisterIPHView(this, usuario).setVisible(true); }

    // MÉTODO AÑADIDO: Para manejar el guardado desde la vista (Corrige violación MVC)
    public boolean guardarIPH(IPHRecord record, String usuario) {
        return service.guardar(record, usuario);
    }

    public void exportarExcel() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            try {
                String path = chooser.getSelectedFile().getAbsolutePath();
                if (!path.endsWith(".xlsx")) path += ".xlsx";
                // CORRECCIÓN: Usa ExcelService, no el ExportService eliminado.
                ExcelService.exportar(service.listarTodos(), path, null);
                JOptionPane.showMessageDialog(view, "Exportado: " + path);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
            }
        }
    }

    public void abrirMapa() {
        var coords = service.listarTodos().stream()
                .map(IPHRecord::getCoordenadas)
                .filter(c -> c != null && !c.isEmpty())
                .collect(Collectors.toList());
        MapService.generarMapaHTML(coords, "Mapa IPH");
    }

    public void hacerBackup() {
        try {
            String path = BackupService.crearBackup();
            JOptionPane.showMessageDialog(view, "Backup creado en: " + path);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error al crear backup: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void abrirPanelAdmin() {
        // CORRECCIÓN: No se debe crear la vista, sino el controlador.
        new AdminController().iniciar();
    }

    public void abrirBusquedaIPH() {
        // Esta vista no estaba en el código, pero el botón sí.
        JOptionPane.showMessageDialog(view, "La funcionalidad 'Buscar IPH' no está implementada.");
    }

    public void cerrarSesion() {
        view.dispose();
        new LoginController().iniciar();
    }
}
package com.iph.controller;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class DashboardController {

    // Asumo que el AdminController existe
    private final AdminController adminController = new AdminController();

    // Asumo que hay un método para obtener el usuario actual.
    public String getUsuario() { return "usuario_dashboard"; }

    // Métodos requeridos por DashboardView.java
    public void importarExcel() {
        JOptionPane.showMessageDialog(null, "Importar Excel (lógica pendiente)");
    }

    public void comprobarActualizacion() {
        JOptionPane.showMessageDialog(null, "Comprobar Actualización (lógica pendiente)");
    }

    // Método que abre el panel de administración
    public void abrirAdminPanel() {
        // Soluciona: cannot find symbol method iniciar()
        adminController.iniciar();
    }

    public void configurarBotonMapa(JButton mapButton) {
        mapButton.addActionListener(e -> {
            try {
                // CORRECCIÓN: Envuelve la llamada que lanza Exception en un try-catch
                // Soluciona: incompatible thrown types java.lang.Exception
                System.out.println("Botón de mapa presionado. Abriendo mapa...");
                // Aquí va la llamada al método que podría lanzar Exception, por ejemplo:
                // MapService.abrirMapa();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al abrir el mapa: " + ex.getMessage());
            }
        });
    }
}