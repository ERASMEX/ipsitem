// --- [File: src/main/java/com/iph/view/AdminPanelView.java] ---
package com.iph.view;

import com.iph.controller.AdminController;
import com.iph.model.AuditLog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminPanelView extends JFrame {
    private final AdminController controller;
    private JPanel panelEstadisticas;
    private JTable tablaAuditoria;
    private DefaultTableModel modelAuditoria;
    private JTextField txtFiltro;
    private JProgressBar progressBar;

    public AdminPanelView(AdminController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setSize(1200, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelEstadisticas = new JPanel(new GridLayout(1, 4, 10, 10)); // 1 fila
        panelEstadisticas.setBorder(BorderFactory.createTitledBorder("Estadísticas del Sistema"));
        add(panelEstadisticas, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createTitledBorder("Registro de Actividad"));

        JPanel panelFiltro = new JPanel();
        panelFiltro.add(new JLabel("Filtrar:"));
        txtFiltro = new JTextField(30);
        txtFiltro.addActionListener(e -> controller.filtrarAuditoria(txtFiltro.getText()));
        panelFiltro.add(txtFiltro);
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> { txtFiltro.setText(""); controller.cargarAuditoria(); });
        panelFiltro.add(btnLimpiar);
        panelCentral.add(panelFiltro, BorderLayout.NORTH);

        String[] cols = {"ID", "Acción", "Informe", "Usuario", "Fecha/Hora", "Cambios"};
        modelAuditoria = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaAuditoria = new JTable(modelAuditoria);
        tablaAuditoria.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Para scroll horizontal

        // Tamaños de columna
        tablaAuditoria.getColumnModel().getColumn(0).setPreferredWidth(50);
        tablaAuditoria.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablaAuditoria.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaAuditoria.getColumnModel().getColumn(3).setPreferredWidth(100);
        tablaAuditoria.getColumnModel().getColumn(4).setPreferredWidth(150);
        tablaAuditoria.getColumnModel().getColumn(5).setPreferredWidth(300);

        JScrollPane scroll = new JScrollPane(tablaAuditoria);
        panelCentral.add(scroll, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnUsuarios = new JButton("Gestión de Usuarios");
        btnUsuarios.addActionListener(e -> controller.abrirGestionUsuarios());
        panelAcciones.add(btnUsuarios);

        JButton btnBackup = new JButton("Crear Backup");
        btnBackup.addActionListener(e -> controller.crearBackup());
        panelAcciones.add(btnBackup);

        JButton btnExportar = new JButton("Exportar Auditoría");
        btnExportar.addActionListener(e -> controller.exportarAuditoria());
        panelAcciones.add(btnExportar);

        JButton btnLimpiarLogs = new JButton("Limpiar Logs Antiguos");
        btnLimpiarLogs.addActionListener(e -> controller.limpiarLogsAntiguos());
        panelAcciones.add(btnLimpiarLogs);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(200, 25));
        progressBar.setVisible(false);
        panelAcciones.add(progressBar);

        add(panelAcciones, BorderLayout.SOUTH);
    }

    public void actualizarEstadistica(String titulo, String valor) {
        for (Component c : panelEstadisticas.getComponents()) {
            if (c instanceof JPanel p && p.getComponentCount() > 0 && p.getComponent(0) instanceof JLabel l && titulo.equals(l.getText())) {
                ((JLabel) p.getComponent(1)).setText(valor);
                return;
            }
        }
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createEtchedBorder());
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(lblTitulo, BorderLayout.NORTH);
        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Arial", Font.BOLD, 24));
        lblValor.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(lblValor, BorderLayout.CENTER);
        panelEstadisticas.add(card);
        panelEstadisticas.revalidate();
    }

    public void actualizarTablaAuditoria(List<AuditLog> logs) {
        modelAuditoria.setRowCount(0);
        for (AuditLog log : logs) {
            modelAuditoria.addRow(new Object[]{
                    log.getId(),
                    log.getAccion(),
                    log.getNumeroInforme(),
                    log.getUsuario(),
                    log.getFechaHora(),
                    log.getCambios()
            });
        }
    }

    public void mostrarProgresoExportacion(boolean mostrar) {
        progressBar.setVisible(mostrar);
        if (mostrar) progressBar.setString("Exportando...");
    }
}