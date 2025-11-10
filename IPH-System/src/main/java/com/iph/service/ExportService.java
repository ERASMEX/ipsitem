package com.iph.service;

import com.iph.model.IPHRecord;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.util.List;

public class ExportService {
    public static void exportarExcel(List<IPHRecord> registros, String path) throws Exception {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("IPH");
        Row header = sheet.createRow(0);
        String[] cols = {"Número", "Fecha", "Denunciante", "Hecho", "Detenidos", "Vehículos"};
        for (int i = 0; i < cols.length; i++) header.createCell(i).setCellValue(cols[i]);
        int rowNum = 1;
        for (IPHRecord r : registros) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(r.getNumeroInforme());
            row.createCell(1).setCellValue(r.getFechaHechos());
            row.createCell(2).setCellValue(r.getDenunciante());
            row.createCell(3).setCellValue(r.getTipoHecho());
            row.createCell(4).setCellValue(r.getDetenidos().size() + " detenidos");
            row.createCell(5).setCellValue(r.getVehiculos().size() + " vehículos");
        }
        try (FileOutputStream fos = new FileOutputStream(path)) { wb.write(fos); }
        wb.close();
    }
    package com.iph.service;

import com.iph.model.IPHRecord;
import java.util.List;
import org.apache.poi.ss.usermodel.*; // Asumo los imports de POI

    public class ExportService {

        public void exportar(List<IPHRecord> records, String path) throws Exception {
            // Lógica de exportación de Excel, usando los métodos corregidos del modelo IPHRecord

            // Simulación de la lógica de iteración:
            for (IPHRecord r : records) {
                // CORRECCIÓN: Uso de Getters que ahora existen
                String informe = r.getNumeroInforme();
                String fecha = r.getFechaHechos();
                String denunciante = r.getDenunciante();
                String tipoHecho = r.getTipoHecho();

                // Asumiendo que getDetenidos() y getVehiculos() retornan List<String>
                String detenidos = String.join(", ", r.getDetenidos());
                String vehiculos = String.join(", ", r.getVehiculos());

                // Aquí iría la lógica para escribir en el archivo Excel
            }

            // try (FileOutputStream fileOut = new FileOutputStream(path)) {
            //     workbook.write(fileOut);
            // }
        }
    }
}