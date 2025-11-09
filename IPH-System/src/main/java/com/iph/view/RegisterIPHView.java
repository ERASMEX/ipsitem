// --- [File: src/main/java/com/iph/view/RegisterIPHView.java] ---
package com.iph.view;

import com.iph.controller.DashboardController;
import com.iph.model.Detenido;
import com.iph.model.IPHRecord;
import com.iph.model.Vehiculo;
import com.iph.util.DateUtil;
import com.iph.util.ValidationUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegisterIPHView extends JDialog {
    private final DashboardController controller;
    private final String usuario;

    // CORRECCIÓN: Se renombra txtNumero a txtInforme para coincidir con el código
    private JTextField txtInforme, txtDenunciante, txtTipoDelito, txtLugar, txtAutoridad, txtPuesta, txtVictima, txtCoords;
    private JComboBox<String> cbTipoHecho;
    private JSpinner spFecha;
    private JTextArea txtObservaciones;
    private DefaultTableModel modelDetenidos, modelVehiculos;

    public RegisterIPHView(DashboardController controller, String usuario) {
        super((Frame) null, "Registrar IPH", true);
        this.controller = controller;
        this.usuario = usuario;
        // CORRECCIÓN: El servicio se quita de la vista, se usará el controlador
        initUI();
    }

    private void initUI() {
        setSize(1000, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int y = 0;

        gbc.gridx = 0; gbc.gridy = y; form.add(new JLabel("Número Informe *"), gbc);
        txtInforme = new JTextField(15); gbc.gridx = 1; form.add(txtInforme, gbc);
        gbc.gridx = 2; form.add(new JLabel("Fecha Hechos *"), gbc);
        spFecha = new JSpinner(new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spFecha, "yyyy-MM-dd");
        spFecha.setEditor(editor);
        gbc.gridx = 3; form.add(spFecha, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; form.add(new JLabel("Denunciante *"), gbc);
        txtDenunciante = new JTextField(20); gbc.gridx = 1; gbc.gridwidth = 3; form.add(txtDenunciante, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 1; form.add(new JLabel("Tipo Hecho *"), gbc);
        cbTipoHecho = new JComboBox<>(new String[]{"", "IP (General)", "Robo", "Homicidio", "Fraude", "Lesiones", "Otro"});
        gbc.gridx = 1; form.add(cbTipoHecho, gbc);
        gbc.gridx = 2; form.add(new JLabel("Tipo Delito"), gbc);
        txtTipoDelito = new JTextField(15); gbc.gridx = 3; form.add(txtTipoDelito, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; form.add(new JLabel("Lugar"), gbc);
        txtLugar = new JTextField(20); gbc.gridx = 1; gbc.gridwidth = 3; form.add(txtLugar, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 1; form.add(new JLabel("Autoridad"), gbc);
        txtAutoridad = new JTextField(15); gbc.gridx = 1; form.add(txtAutoridad, gbc);
        gbc.gridx = 2; form.add(new JLabel("Puesta a disposición"), gbc);
        txtPuesta = new JTextField(15); gbc.gridx = 3; form.add(txtPuesta, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; form.add(new JLabel("Víctima"), gbc);
        txtVictima = new JTextField(20); gbc.gridx = 1; gbc.gridwidth = 3; form.add(txtVictima, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 1; form.add(new JLabel("Coordenadas (lat,lon)"), gbc);
        txtCoords = new JTextField(20); gbc.gridx = 1; form.add(txtCoords, gbc);
        JButton btnMapa = new JButton("Validar / Ver");
        btnMapa.addActionListener(e -> abrirMapaTemp());
        gbc.gridx = 2; form.add(btnMapa, gbc);

        // Tablas
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Detenidos (max 10)", createPanelDetenidos());
        tabbedPane.addTab("Vehículos (max 10)", createPanelVehiculos());

        y++;
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 4; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        form.add(tabbedPane, gbc);
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        y++;
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 1; form.add(new JLabel("Observaciones"), gbc);
        txtObservaciones = new JTextArea(4, 50);
        JScrollPane scrollObs = new JScrollPane(txtObservaciones);
        gbc.gridx = 1; gbc.gridwidth = 3;
        gbc.gridy = y; form.add(scrollObs, gbc);

        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Guardar IPH");
        btnGuardar.addActionListener(e -> guardarIPH());
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        add(new JScrollPane(form), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private JPanel createPanelDetenidos() {
        JPanel panel = new JPanel(new BorderLayout());
        modelDetenidos = new DefaultTableModel(new String[]{"Nombre", "Sexo"}, 0);
        JTable tablaDet = new JTable(modelDetenidos);
        panel.add(new JScrollPane(tablaDet), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAddDet = new JButton("Agregar");
        btnAddDet.addActionListener(e -> agregarDetenido());
        JButton btnDelDet = new JButton("Eliminar");
        btnDelDet.addActionListener(e -> eliminarFila(tablaDet));
        panelBotones.add(btnAddDet); panelBotones.add(btnDelDet);
        panel.add(panelBotones, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createPanelVehiculos() {
        JPanel panel = new JPanel(new BorderLayout());
        modelVehiculos = new DefaultTableModel(new String[]{"Tipo", "Marca", "Placa", "Serie"}, 0);
        JTable tablaVeh = new JTable(modelVehiculos);
        panel.add(new JScrollPane(tablaVeh), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAddVeh = new JButton("Agregar");
        btnAddVeh.addActionListener(e -> agregarVehiculo());
        JButton btnDelVeh = new JButton("Eliminar");
        btnDelVeh.addActionListener(e -> eliminarFila(tablaVeh));
        panelBotones.add(btnAddVeh); panelBotones.add(btnDelVeh);
        panel.add(panelBotones, BorderLayout.SOUTH);
        return panel;
    }


    private void abrirMapaTemp() {
        String coords = txtCoords.getText().trim();
        // CORRECCIÓN: Usar ValidationUtil
        if (!ValidationUtil.esCoordenadaValida(coords)) {
            JOptionPane.showMessageDialog(this, "Ingrese coordenadas válidas (ej: 20.123456, -100.123456)", "Mapa", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Formatear
        coords = ValidationUtil.formatearCoordenadas(coords);
        txtCoords.setText(coords);

        try {
            String[] parts = coords.split(",");
            String html = """
                <!DOCTYPE html><html><head><meta charset='utf-8'>
                <title>Mapa IPH</title>
                <link rel='stylesheet' href='https://unpkg.com/leaflet@1.9.4/dist/leaflet.css'/>
                <script src='https://unpkg.com/leaflet@1.9.4/dist/leaflet.js'></script>
                <style>#map { height: 100vh; }</style>
                </head><body>
                <div id='map'></div>
                <script>
                var map = L.map('map').setView([%s, %s], 15);
                L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png').addTo(map);
                L.marker([%s, %s]).addTo(map).bindPopup('Lugar del hecho').openPopup();
                </script></body></html>
                """.formatted(parts[0].trim(), parts[1].trim(), parts[0].trim(), parts[1].trim());
            java.io.File temp = java.io.File.createTempFile("iph_mapa_", ".html");
            try (java.io.FileWriter w = new java.io.FileWriter(temp)) {
                w.write(html);
            }
            java.awt.Desktop.getDesktop().browse(temp.toURI());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al abrir mapa");
        }
    }

    private void agregarDetenido() {
        if (modelDetenidos.getRowCount() >= 10) {
            JOptionPane.showMessageDialog(this, "Máximo 10 detenidos");
            return;
        }
        String nombre = JOptionPane.showInputDialog(this, "Nombre del detenido:");
        if (nombre == null || nombre.trim().isEmpty()) return;
        String sexo = (String) JOptionPane.showInputDialog(
                this, "Sexo:", "Sexo", JOptionPane.QUESTION_MESSAGE,
                null, new String[]{"Hombre", "Mujer", "No especificado"}, "Hombre");
        if (sexo != null) {
            modelDetenidos.addRow(new Object[]{nombre.trim(), sexo});
        }
    }

    private void agregarVehiculo() {
        if (modelVehiculos.getRowCount() >= 10) {
            JOptionPane.showMessageDialog(this, "Máximo 10 vehículos");
            return;
        }
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField t1 = new JTextField(10), t2 = new JTextField(10), t3 = new JTextField(10), t4 = new JTextField(10);
        panel.add(new JLabel("Tipo:")); panel.add(t1);
        panel.add(new JLabel("Marca:")); panel.add(t2);
        panel.add(new JLabel("Placa:")); panel.add(t3);
        panel.add(new JLabel("Serie:")); panel.add(t4);

        int res = JOptionPane.showConfirmDialog(this, panel, "Agregar Vehículo", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            // Se pueden agregar vehículos sin datos, si es necesario.
            modelVehiculos.addRow(new Object[]{t1.getText().trim(), t2.getText().trim(), t3.getText().trim(), t4.getText().trim()});
        }
    }

    private void eliminarFila(JTable tabla) {
        int row = tabla.getSelectedRow();
        if (row >= 0) {
            ((DefaultTableModel) tabla.getModel()).removeRow(row);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
        }
    }

    private void guardarIPH() {
        if (!validarCampos()) return;
        IPHRecord record = new IPHRecord();
        record.setNumeroInforme(txtInforme.getText().trim());
        record.setFechaHechos(DateUtil.formatDate((java.util.Date) spFecha.getValue()));
        record.setDenunciante(txtDenunciante.getText().trim());
        record.setTipoHecho((String) cbTipoHecho.getSelectedItem());
        record.setTipoDelito(txtTipoDelito.getText().trim());
        record.setLugar(txtLugar.getText().trim());
        record.setAutoridad(txtAutoridad.getText().trim());
        record.setPuestaDisposicion(txtPuesta.getText().trim());
        record.setVictima(txtVictima.getText().trim());
        record.setCoordenadas(txtCoords.getText().trim());
        record.setObservaciones(txtObservaciones.getText());
        record.setEstadoProcesal("En trámite");

        List<Detenido> detenidos = new ArrayList<>();
        for (int i = 0; i < modelDetenidos.getRowCount(); i++) {
            detenidos.add(new Detenido(
                    (String) modelDetenidos.getValueAt(i, 0),
                    (String) modelDetenidos.getValueAt(i, 1)
            ));
        }
        record.setDetenidos(detenidos);

        List<Vehiculo> vehiculos = new ArrayList<>();
        for (int i = 0; i < modelVehiculos.getRowCount(); i++) {
            vehiculos.add(new Vehiculo(
                    (String) modelVehiculos.getValueAt(i, 0),
                    (String) modelVehiculos.getValueAt(i, 1),
                    (String) modelVehiculos.getValueAt(i, 2),
                    (String) modelVehiculos.getValueAt(i, 3)
            ));
        }
        record.setVehiculos(vehiculos);

        // CORRECCIÓN: Llamar al controlador en lugar de al servicio
        if (controller.guardarIPH(record, usuario)) {
            JOptionPane.showMessageDialog(this, "IPH registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar el IPH (posible número de informe duplicado).", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarCampos() {
        if (txtInforme.getText().trim().isEmpty() ||
                txtDenunciante.getText().trim().isEmpty() ||
                cbTipoHecho.getSelectedItem() == null ||
                cbTipoHecho.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos con * son obligatorios", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        String coords = txtCoords.getText().trim();
        if (!coords.isEmpty() && !ValidationUtil.esCoordenadaValida(coords)) {
            JOptionPane.showMessageDialog(this, "El formato de coordenadas no es válido.", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }
}