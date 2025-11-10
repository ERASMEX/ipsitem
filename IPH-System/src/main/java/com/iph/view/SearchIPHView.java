package com.iph.view;

import com.iph.controller.Controller; // CORRECCIÓN
import com.iph.service.ExcelService;
import com.iph.service.ExcelService.ImportResult;
import javax.swing.*;
import java.util.List;

public class SearchIPHView extends JDialog {

    private final Controller controller;
    private JProgressBar progressBar;

    public SearchIPHView(JFrame parent, Controller controller) {
        super(parent, "Búsqueda de IPH", true);
        this.controller = controller;
        this.progressBar = new JProgressBar();
        // ... Lógica de inicialización de la interfaz
    }

    private void realizarBusqueda() { /* Lógica para refrescar la tabla */ }
    private void mostrarReporteErrores(List<String> errores) { /* Lógica para mostrar errores */ }

    private void importarExcel() {
        JFileChooser chooser = new JFileChooser();
        String path = "ruta/ficticia.xlsx"; // Debe obtenerse del chooser

        progressBar.setValue(0);
        progressBar.setString("Validando...");

        // Usamos controller.getUsuario()
        new ExcelImportWorker(path, controller.getUsuario()).execute();
    }

    private class ExcelImportWorker extends SwingWorker<ImportResult, Void> {
        private final String path;
        private final String usuario;

        public ExcelImportWorker(String path, String usuario) {
            this.path = path;
            this.usuario = usuario;
        }

        @Override
        protected ImportResult doInBackground() throws Exception {
            return ExcelService.importarConErrores(path, usuario, progressBar);
        }

        @Override
        protected void done() {
            try {
                ImportResult result = get();
                // Lógica para mostrar resultados y errores
            } catch (Exception e) {
                JOptionPane.showMessageDialog(SearchIPHView.this, "Error en la importación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            progressBar.setValue(0);
            progressBar.setString(null);
        }
    }
}