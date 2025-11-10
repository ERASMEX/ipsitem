package com.iph.controller;

import com.iph.dao.AuditDAO;
import com.iph.service.AuditService;
import com.iph.model.AuditLog;
import com.iph.service.UserService;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class AdminController extends Controller {

    private final AuditService auditService;

    public AdminController() {
        this.auditService = new AuditService(new AuditDAO());
    }

    // Soluciona: cannot find symbol method iniciar() (llamado desde DashboardController)
    public void iniciar() {
        JOptionPane.showMessageDialog(null, "Abriendo Admin Panel (vista pendiente)");
        // Aquí se inicializaría y mostraría AdminPanelView
        // new AdminPanelView(this).setVisible(true);
    }

    // Métodos requeridos por AdminPanelView.java
    public int getIPHCount() { return auditService.contarIPH(); }
    public int getIPHCountToday() { return auditService.contarIPHHoy(); }
    public int getUserCount() { return auditService.contarUsuarios(); }

    public List<AuditLog> listarAuditoria() { return auditService.listarTodos(); }

    // Soluciona: cannot find symbol method filtrarAuditoria
    public List<AuditLog> filtrarAuditoria(String filtro) {
        return auditService.buscar(filtro);
    }

    // Soluciona: cannot find symbol method cargarAuditoria
    public void cargarAuditoria() {
        // Lógica para cargar y mostrar la lista de auditoría en la vista
    }

    // Soluciona: cannot find symbol method abrirGestionUsuarios
    public void abrirGestionUsuarios() {
        JOptionPane.showMessageDialog(null, "Abriendo Gestión Usuarios (vista pendiente)");
    }

    // Soluciona: cannot find symbol method crearBackup
    public void crearBackup() {
        JOptionPane.showMessageDialog(null, "Creando Backup (lógica pendiente)");
    }

    // Soluciona: method exportarAuditoria cannot be applied to given types (la vista llama sin argumentos)
    public void exportarAuditoria() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Auditoría");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            exportarAuditoria(path);
        }
    }

    // Método con firma de String para lógica de negocio
    private void exportarAuditoria(String path) {
        try {
            auditService.exportarExcel(path);
            JOptionPane.showMessageDialog(null, "Auditoría exportada exitosamente a: " + path);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al exportar auditoría: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Soluciona: cannot find symbol method limpiarLogsAntiguos
    public void limpiarLogsAntiguos() {
        int dias = 90;
        try {
            auditService.eliminarAntiguos(dias);
            JOptionPane.showMessageDialog(null, String.format("Logs anteriores a %d días eliminados.", dias));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar auditoría: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}