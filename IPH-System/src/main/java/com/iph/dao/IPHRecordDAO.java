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
}