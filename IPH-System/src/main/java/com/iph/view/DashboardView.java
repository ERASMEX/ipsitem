// --- [File: src/main/java/com/iph/view/DashboardView.java] ---
package com.iph.view;

import com.iph.controller.DashboardController;
import com.iph.util.IconUtil;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap; // Using LinkedHashMap to maintain order

public class DashboardView extends JFrame {

    private final DashboardController controller;

    public DashboardView(DashboardController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("IPH System - " + controller.getUsuario());
        setSize(1400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the JSplitPane to hold the sidebar and the main content area
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        split.setLeftComponent(createSidebar());
        // Initial right component
        split.setRightComponent(new JLabel("Seleccione una opción", SwingConstants.CENTER));

        // Set an initial size for the sidebar
        split.setDividerLocation(250); // Set sidebar width
        split.setDividerSize(5);

        add(split);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(11, 22, 32));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        // Add some initial padding at the top
        sidebar.add(Box.createVerticalStrut(20));

        // Define buttons with their display text, icon constant, and action
        LinkedHashMap<String, Runnable> buttonActions = new LinkedHashMap<>();
        buttonActions.put("Registrar IPH", () -> controller.abrirRegistroIPH()); // Assuming a no-arg method for the button
        buttonActions.put("Buscar IPH", () -> controller.abrirBusquedaIPH());
        buttonActions.put("Importar Excel", () -> controller.importarExcel());
        buttonActions.put("Exportar Excel", () -> controller.exportarExcel());
        buttonActions.put("Ver Mapa", () -> controller.abrirMapa());
        buttonActions.put("Backup", () -> controller.hacerBackup());
        buttonActions.put("Comprobar Actualización", () -> controller.comprobarActualizacion());
        buttonActions.put("Panel Admin", () -> controller.abrirPanelAdmin());

        // Add "Cerrar Sesión" last
        buttonActions.put("Cerrar Sesión", () -> controller.cerrarSesion());

        // Helper method to create styled buttons
        for (String text : buttonActions.keySet()) {
            JButton btn = createStyledSidebarButton(text);

            // Apply icon based on the button text
            switch(text) {
                case "Registrar IPH" -> IconUtil.applyIcon(btn, IconUtil.REGISTER);
                case "Buscar IPH" -> IconUtil.applyIcon(btn, IconUtil.SEARCH);
                case "Importar Excel" -> IconUtil.applyIcon(btn, IconUtil.EXCEL_IMPORT);
                case "Exportar Excel" -> IconUtil.applyIcon(btn, IconUtil.EXCEL_EXPORT);
                case "Ver Mapa" -> IconUtil.applyIcon(btn, IconUtil.MAP); // Assuming MAP icon exists
                case "Backup" -> IconUtil.applyIcon(btn, IconUtil.BACKUP); // Assuming BACKUP icon exists
                case "Comprobar Actualización" -> IconUtil.applyIcon(btn, IconUtil.UPDATE);
                case "Panel Admin" -> IconUtil.applyIcon(btn, IconUtil.ADMIN); // Assuming ADMIN icon exists
                case "Cerrar Sesión" -> IconUtil.applyIcon(btn, IconUtil.LOGOUT); // Assuming LOGOUT icon exists
            }

            // Add the action listener
            btn.addActionListener(e -> buttonActions.get(text).run());

            sidebar.add(btn);
            sidebar.add(Box.createVerticalStrut(10));
        }

        // Empuja todo hacia arriba, ensuring buttons are at the top
        sidebar.add(Box.createVerticalGlue());
        return sidebar;
    }

    /**
     * Helper method to apply consistent styling to all sidebar buttons.
     */
    private JButton createStyledSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(240, 50));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(16, 35, 43));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false); // Optional: improves look
        btn.setRolloverEnabled(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Optional: visual cue

        // Optional: Add a subtle rollover effect
        btn.addChangeListener(e -> {
            AbstractButton button = (AbstractButton) e.getSource();
            if (button.getModel().isRollover()) {
                button.setBackground(new Color(25, 50, 60)); // Darker on hover
            } else {
                button.setBackground(new Color(16, 35, 43)); // Default
            }
        });
        return btn;
    }
}