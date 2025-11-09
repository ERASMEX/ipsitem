package com.iph.service;

import com.iph.dao.AuditDAO;
import com.iph.model.AuditLog;
import java.util.List;

public class AuditService {

    private final AuditDAO dao;

    // Constructor con dependencia (requerido por AdminController)
    public AuditService(AuditDAO auditDAO) {
        this.dao = auditDAO;
    }

    // Método log estático para llamadas sencillas (requerido por UserService)
    public static void log(String accion, String tabla, String usuario, String detalles) {
        // Crea una nueva instancia temporal del DAO para logging estático
        new AuditDAO().log(new AuditLog(accion, tabla, usuario, detalles));
    }

    // Métodos de negocio requeridos por AdminController
    public int contarIPH() { return dao.contarIPH(); }
    public int contarIPHHoy() { return dao.contarIPHHoy(); }
    public int contarUsuarios() { return dao.contarUsuarios(); }

    public List<AuditLog> listarTodos() { return dao.listarTodos(); }
    public List<AuditLog> buscar(String query) { return dao.buscar(query); }
    public void eliminarAntiguos(int dias) { dao.eliminarAntiguos(dias); }

    public void exportarExcel(String path) throws Exception {
        List<AuditLog> logs = listarTodos();
        // Asumo que ExcelService.exportarAuditoria existe
        ExcelService.exportarAuditoria(logs, path);
    }
}