// --- [File: src/main/java/com/iph/dao/IPHRecordDAO.java] ---
package com.iph.dao;

import com.iph.model.Detenido;
import com.iph.model.IPHRecord;
import com.iph.model.Vehiculo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// CORRECCIÓN: Hereda de BaseDAO
public class IPHRecordDAO extends BaseDAO {

    private final ObjectMapper mapper = new ObjectMapper();

    public boolean insert(IPHRecord record) {
        // CORRECCIÓN: SQL completo con 16 campos
        String sql = """
            INSERT INTO iph (
                numero_informe, fecha_hechos, denunciante, tipo_hecho, tipo_delito,
                lugar, autoridad, puesta_a_disposicion, victima, coordenadas,
                estado_procesal, observaciones, capturado_por, creado_en,
                detenidos_json, vehiculos_json
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // CORRECCIÓN: Seteo de todos los 16 parámetros
            pstmt.setString(1, record.getNumeroInforme());
            pstmt.setString(2, record.getFechaHechos());
            pstmt.setString(3, record.getDenunciante());
            pstmt.setString(4, record.getTipoHecho());
            pstmt.setString(5, record.getTipoDelito());
            pstmt.setString(6, record.getLugar());
            pstmt.setString(7, record.getAutoridad());
            pstmt.setString(8, record.getPuestaDisposicion());
            pstmt.setString(9, record.getVictima());
            pstmt.setString(10, record.getCoordenadas());
            pstmt.setString(11, record.getEstadoProcesal());
            pstmt.setString(12, record.getObservaciones());
            pstmt.setString(13, record.getCapturadoPor());
            pstmt.setString(14, record.getCreadoEn());

            // Serializar JSON
            pstmt.setString(15, mapper.writeValueAsString(record.getDetenidos()));
            pstmt.setString(16, mapper.writeValueAsString(record.getVehiculos()));

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error DB: " + e.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<IPHRecord> findAll() {
        List<IPHRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM iph ORDER BY fecha_hechos DESC";

        // CORRECCIÓN: Fuga de ResultSet y se usa try-with-resources
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                IPHRecord r = new IPHRecord();
                // CORRECCIÓN: Mapeo de todos los campos
                r.setId(rs.getInt("id"));
                r.setNumeroInforme(rs.getString("numero_informe"));
                r.setFechaHechos(rs.getString("fecha_hechos"));
                r.setDenunciante(rs.getString("denunciante"));
                r.setTipoHecho(rs.getString("tipo_hecho"));
                r.setTipoDelito(rs.getString("tipo_delito"));
                r.setLugar(rs.getString("lugar"));
                r.setAutoridad(rs.getString("autoridad"));
                r.setPuestaDisposicion(rs.getString("puesta_a_disposicion"));
                r.setVictima(rs.getString("victima"));
                r.setCoordenadas(rs.getString("coordenadas"));
                r.setEstadoProcesal(rs.getString("estado_procesal"));
                r.setObservaciones(rs.getString("observaciones"));
                r.setCapturadoPor(rs.getString("capturado_por"));
                r.setCreadoEn(rs.getString("creado_en"));

                // Deserializar JSON
                String detJson = rs.getString("detenidos_json");
                r.setDetenidos(detJson != null ? mapper.readValue(detJson, new TypeReference<List<Detenido>>() {}) : new ArrayList<>());
                String vehJson = rs.getString("vehiculos_json");
                r.setVehiculos(vehJson != null ? mapper.readValue(vehJson, new TypeReference<List<Vehiculo>>() {}) : new ArrayList<>());

                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    package com.iph.dao;

import com.iph.model.IPHRecord;
import com.google.gson.Gson; // Necesario para JSON (si tu pom lo incluye)
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import com.iph.config.DatabaseConfig; // Asumo que existe para obtener la conexión

    public class IPHRecordDAO {

        private final Gson gson = new Gson();

        private Connection getConnection() throws Exception {
            return DatabaseConfig.getConnection();
        }

        public void insertar(IPHRecord record) throws Exception {
            // SQL incompleto, pero los parámetros usan los getters corregidos
            String sql = "INSERT INTO iph (numero_informe, fecha_hechos, denunciante, tipo_hecho, tipo_delito, lugar, autoridad, puesta_disposicion, victima, coordenadas, estado_procesal, observaciones, capturado_por, creado_en, detenidos, vehiculos) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                int i = 1;
                // CORRECCIÓN: Uso de Getters que ahora existen
                stmt.setString(i++, record.getNumeroInforme());
                stmt.setString(i++, record.getFechaHechos());
                stmt.setString(i++, record.getDenunciante());
                stmt.setString(i++, record.getTipoHecho());
                stmt.setString(i++, record.getTipoDelito());
                stmt.setString(i++, record.getLugar());
                stmt.setString(i++, record.getAutoridad());
                stmt.setString(i++, record.getPuestaDisposicion());
                stmt.setString(i++, record.getVictima());
                stmt.setString(i++, record.getCoordenadas());
                stmt.setString(i++, record.getEstadoProcesal());
                stmt.setString(i++, record.getObservaciones());
                stmt.setString(i++, record.getCapturadoPor());
                stmt.setString(i++, record.getCreadoEn());

                // Se asume que Detenidos y Vehículos se serializan a JSON String
                stmt.setString(i++, record.getDetenidos() != null ? gson.toJson(record.getDetenidos()) : "[]");
                stmt.setString(i++, record.getVehiculos() != null ? gson.toJson(record.getVehiculos()) : "[]");

                // stmt.executeUpdate();
            }
        }

        public void actualizar(IPHRecord record) throws Exception {
            // Lógica de actualización...
        }

        public boolean existe(String numeroInforme) {
            return false; // Stub
        }

        public List<IPHRecord> buscar(String query) {
            List<IPHRecord> records = new ArrayList<>();
            String sql = "SELECT * FROM iph WHERE ...";
            try (Connection conn = getConnection();
                 PreparedStatement stmt = null; // Asumo PreparedStatement está bien definido
                 ResultSet rs = null) { // Asumo ResultSet está bien definido

                if (false) { // Cambiar a while(rs.next())
                    IPHRecord r = new IPHRecord();

                    // CORRECCIÓN: Uso de Setters que ahora existen
                    r.setNumeroInforme(rs.getString("numero_informe"));
                    r.setFechaHechos(rs.getString("fecha_hechos"));
                    r.setDenunciante(rs.getString("denunciante"));
                    r.setTipoHecho(rs.getString("tipo_hecho"));
                    r.setTipoDelito(rs.getString("tipo_delito"));
                    r.setLugar(rs.getString("lugar"));
                    r.setAutoridad(rs.getString("autoridad"));
                    r.setPuestaDisposicion(rs.getString("puesta_disposicion"));
                    r.setVictima(rs.getString("victima"));
                    r.setCoordenadas(rs.getString("coordenadas"));
                    r.setEstadoProcesal(rs.getString("estado_procesal"));
                    r.setObservaciones(rs.getString("observaciones"));
                    r.setCapturadoPor(rs.getString("capturado_por"));
                    r.setCreadoEn(rs.getString("creado_en"));

                    // Des-serialización de JSON a List<String>
                    String detJson = rs.getString("detenidos_json");
                    String vehJson = rs.getString("vehiculos_json");

                    r.setDetenidos(detJson != null ? Arrays.asList(gson.fromJson(detJson, String[].class)) : new ArrayList<>());
                    r.setVehiculos(vehJson != null ? Arrays.asList(gson.fromJson(vehJson, String[].class)) : new ArrayList<>());

                    records.add(r);
                }
            } catch (Exception e) {
                // Manejo de excepción
            }
            return records;
        }
    }
}