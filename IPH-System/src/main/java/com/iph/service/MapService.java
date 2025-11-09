// --- [File: src/main/java/com/iph/service/MapService.java] ---
package com.iph.service;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class MapService {
    public static void generarMapaHTML(List<String> coordenadas, String titulo) {
        try {
            File temp = File.createTempFile("iph_mapa_", ".html");
            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html><html><head><meta charset='utf-8'>");
            html.append("<title>").append(titulo).append("</title>");
            html.append("<link rel='stylesheet' href='https://unpkg.com/leaflet@1.9.4/dist/leaflet.css'/>");
            html.append("<script src='https://unpkg.com/leaflet@1.9.4/dist/leaflet.js'></script>");
            html.append("<style>#map { height: 100vh; }</style></head><body>");
            html.append("<div id='map'></div><script>");
            html.append("var map = L.map('map').setView([20.0, -100.0], 6);");
            html.append("L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png').addTo(map);");
            for (String coord : coordenadas) {
                if (coord != null && coord.matches("-?\\d+\\.\\d+,-?\\d+\\.\\d+")) {
                    String[] p = coord.split(",");
                    html.append(String.format("L.marker([%s, %s]).addTo(map).bindPopup('IPH');", p[0].trim(), p[1].trim()));
                }
            }
            html.append("</script></body></html>");

            try (FileWriter w = new FileWriter(temp)) { w.write(html.toString()); }
            java.awt.Desktop.getDesktop().browse(temp.toURI());
        } catch (Exception e) { e.printStackTrace(); }
    }
}