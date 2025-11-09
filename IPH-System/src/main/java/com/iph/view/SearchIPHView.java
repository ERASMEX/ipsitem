package com.iph.view;

import com.iph.controller.Controller; // Requerido por el error de símbolo
import com.iph.service.ExcelService;
import com.iph.service.ExcelService.ImportResult;
import javax.swing.*;
import java.util.List;

// Asumo que SearchIPHView extiende JDialog para ser una ventana secundaria
public class SearchIPHView extends JDialog {

    private final Controller controller;
    private JProgressBar progressBar;

    // Constructor
    public SearchIPHView(JFrame parent, Controller controller) {
        super(parent, "Búsqueda de IPH", true);
        this.controller = controller;
        this.progressBar = new JProgressBar();
        // Lógica de inicialización de la interfaz
        this.setSize(800, 600);
        this.setLocationRelativeTo(parent);
    }

    // Asumo que el Controller tiene este método
    // Necesario para evitar el error de "cannot find symbol: variable path"
    public String getPath() { return "ruta/por/defecto.xlsx"; }

    private void realizarBusqueda() { /* Lógica para refrescar la tabla */ }
    private void mostrarReporteErrores(List<String> errores) { /* Lógica para mostrar errores */ }

    // Método que inicia el proceso de importación
    private void importarExcel() {
        JFileChooser chooser = new JFileChooser();
        // ... (Tu lógica de selección de archivo) ...
        String path = getPath(); // Usamos un método para obtener el path

        progressBar.setValue(0);
        progressBar.setString("Validando...");

        // Ejecutar el SwingWorker
        // Asumo que controller.getUsuario() devuelve el nombre de usuario
        new ExcelImportWorker(path, controller.getUsuario()).execute();
    }

    /**
     * Implementación de SwingWorker para la importación en segundo plano.
     */
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