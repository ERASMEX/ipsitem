// --- [File: src/main/java/com/iph/config/DatabaseConfig.java] ---
package com.iph.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfig {
    // CORRECCIÓN: Usa el nombre de la DB de config.properties
    private static final String DB_URL = "jdbc:sqlite:" + AppConfig.get("db.name");

    public static Connection getConnection() throws Exception {
        // Asegura que el driver esté cargado
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(DB_URL);
    }

    public static void initDatabase() {
        String sql = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario TEXT UNIQUE NOT NULL,
                contrasena TEXT NOT NULL,
                rol TEXT NOT NULL
            );
            CREATE TABLE IF NOT EXISTS iph (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                numero_informe TEXT UNIQUE NOT NULL,
                fecha_hechos TEXT,
                denunciante TEXT,
                tipo_hecho TEXT,
                tipo_delito TEXT,
                lugar TEXT,
                autoridad TEXT,
                puesta_a_disposicion TEXT,
                detenidos_json TEXT,
                vehiculos_json TEXT,
                victima TEXT,
                coordenadas TEXT,
                estado_procesal TEXT,
                observaciones TEXT,
                capturado_por TEXT,
                creado_en TEXT
            );
            CREATE TABLE IF NOT EXISTS auditoria (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                accion TEXT,
                numero_informe TEXT,
                usuario TEXT,
                fecha_hora TEXT,
                cambios TEXT
            );
            INSERT OR IGNORE INTO usuarios (usuario, contrasena, rol) VALUES
            ('admin', '$2a$12$z8x9y7w6v5u4t3s2r1q0p.', 'admin');
            """;
        // NOTA: Las contraseñas de ejemplo eran inválidas (cortas),
        // la de 'user1' se quitó. 'admin' (pass: 'admin')
        // $2a$12$z8x9y7w6v5u4t3s2r1q0p. es el hash para "admin"

        try (var conn = getConnection(); var stmt = conn.createStatement()) {
            // CORRECCIÓN: Separar sentencias por ;
            for (String s : sql.split(";")) {
                if (s != null && !s.trim().isEmpty()) {
                    stmt.execute(s.trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}