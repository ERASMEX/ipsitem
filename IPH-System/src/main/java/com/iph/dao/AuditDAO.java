package com.iph.dao;

import com.iph.model.AuditLog;
import com.iph.config.DatabaseConfig;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class AuditDAO {
    // Nota: Lógica de conexión SQL omitida, solo se definen las firmas requeridas

    public void log(AuditLog log) {
        // Implementación: Insertar log en la base de datos
    }

    // Métodos requeridos por AuditService y AdminController
    public int contarIPH() { return 0; }
    public int contarIPHHoy() { return 0; }
    public int contarUsuarios() { return 0; }

    public List<AuditLog> listarTodos() {
        // Implementación: Consultar todos los logs
        return new ArrayList<>();
    }
    public List<AuditLog> buscar(String query) {
        // Implementación: Buscar logs
        return new ArrayList<>();
    }
    public void eliminarAntiguos(int dias) {
        // Implementación: Eliminar logs antiguos
    }
}