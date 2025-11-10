package com.iph.view;

import com.iph.controller.AdminController;
import com.iph.model.AuditLog;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

public class AdminPanelView extends JDialog {
    private final AdminController controller;
    private JTable tablaAuditoria;

    public AdminPanelView(JFrame parent, AdminController controller) {
        super(parent, "Panel de Administración", true);
        this.controller = controller;
        // ... inicialización de componentes
    }

    private void configurarBotones() {
        JButton btnBuscar = new JButton("Buscar");
        JTextField txtFiltro = new JTextField(20);

        // Soluciona: cannot find symbol method filtrarAuditoria
        btnBuscar.addActionListener(e -> {
            String filtro = txtFiltro.getText();
            mostrarAuditoria(controller.filtrarAuditoria(filtro));
        });

        JButton btnGestionUsuarios = new JButton("Gestionar Usuarios");
        // Soluciona: cannot find symbol method abrirGestionUsuarios
        btnGestionUsuarios.addActionListener(e -> controller.abrirGestionUsuarios());

        JButton btnCrearBackup = new JButton("Crear Backup");
        // Soluciona: cannot find symbol method crearBackup
        btnCrearBackup.addActionListener(e -> controller.crearBackup());

        JButton btnExportar = new JButton("Exportar Auditoría");
        // Soluciona: method exportarAuditoria cannot be applied to given types
        btnExportar.addActionListener(e -> controller.exportarAuditoria());

        JButton btnLimpiarLogs = new JButton("Limpiar Logs Antiguos");
        // Soluciona: cannot find symbol method limpiarLogsAntiguos
        btnLimpiarLogs.addActionListener(e -> controller.limpiarLogsAntiguos());
    }

    // Soluciona: cannot find symbol method cargarAuditoria
    public void cargarAuditoria() {
        List<AuditLog> logs = controller.listarAuditoria();
        mostrarAuditoria(logs);
    }

    private void mostrarAuditoria(List<AuditLog> logs) {
        DefaultTableModel model = (DefaultTableModel) tablaAuditoria.getModel();
        model.setRowCount(0); // Limpiar tabla

        for (AuditLog log : logs) {
            Vector<Object> row = new Vector<>();
            // CORRECCIÓN: Uso de Getters alternativos definidos en AuditLog.java
            row.add(log.getNumeroInforme()); // Soluciona: getNumeroInforme()
            row.add(log.getUsuario());
            row.add(log.getAccion());
            row.add(log.getFechaHora());     // Soluciona: getFechaHora()
            row.add(log.getCambios());       // Soluciona: getCambios()
            model.addRow(row);
        }
    }
    // ... el resto de la clase
}