package com.iph.view;

import com.iph.controller.DashboardController;
import com.iph.util.IconUtil; // Importación necesaria
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardView extends JFrame {

    private final DashboardController controller;

    public DashboardView(DashboardController controller) {
        this.controller = controller;
        setTitle("Dashboard IPH");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        // ... más inicialización de vista

        // Simulación de los componentes con error
        JButton btnImportar = new JButton("Importar IPH");
        JButton btnActualizar = new JButton("Comprobar Actualización");
        JButton btnMapa = new JButton("Mapa", IconUtil.MAP); // CORRECCIÓN: Uso de IconUtil.MAP
        JButton btnBackup = new JButton("Backup", IconUtil.BACKUP); // CORRECCIÓN: Uso de IconUtil.BACKUP

        // CORRECCIÓN: Llamada a métodos que ahora existen en DashboardController
        btnImportar.addActionListener(e -> controller.importarExcel());
        btnActualizar.addActionListener(e -> controller.comprobarActualizacion());

        controller.configurarBotonMapa(btnMapa);
        // ... el resto de la configuración de botones
    }
}