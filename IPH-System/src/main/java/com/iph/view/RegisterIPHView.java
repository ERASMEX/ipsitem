package com.iph.view;

import com.iph.controller.DashboardController;
import com.iph.model.IPHRecord;
import javax.swing.*;
import com.iph.util.DateUtil;
import java.awt.event.ActionEvent;

public class RegisterIPHView extends JDialog {

    private final DashboardController controller;

    public RegisterIPHView(JFrame parent, DashboardController controller) {
        super(parent, "Registro de IPH", true);
        this.controller = controller;
        // ...
        setupListeners();
    }

    // Método que simula la lógica de guardado
    private void guardarRegistro() {
        // Simulación: Crear el objeto IPHRecord (asumiendo que los datos fueron leídos de la UI)
        IPHRecord record = new IPHRecord();
        String username = controller.getUsuario();

        // CORRECCIÓN: Llama al método guardarIPH en el controlador
        // Esto resuelve el error en la línea [272,23] (o la línea equivalente en tu código)
        controller.guardarIPH(record, username); // Llama al método con la firma correcta

        JOptionPane.showMessageDialog(this, "IPH registrado exitosamente.");
        this.dispose();
    }

    // Placeholder para simular la estructura
    private void setupListeners() {
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarRegistro());
        // ...
    }
}