// --- [File: src/main/java/com/iph/service/IPHService.java] ---
package com.iph.service;

import com.iph.dao.AuditDAO;
import com.iph.dao.IPHRecordDAO;
import com.iph.model.AuditLog;
import com.iph.model.IPHRecord;
import java.util.List;
import java.time.LocalDateTime;

// Placeholder for a custom exception and logger
class ServiceException extends Exception {
    public ServiceException(String message) { super(message); }
    public ServiceException(String message, Throwable cause) { super(message, cause); }
}

class Logger {
    public void error(String message) { System.err.println("[ERROR] " + message); }
}

public class IPHService {
    private final IPHRecordDAO dao = new IPHRecordDAO();
    private final AuditDAO auditDAO = new AuditDAO();
    // Added AuditService for consistency with the llamarLote implementation
    private final AuditService auditService = new AuditService(auditDAO);
    private final Logger log = new Logger(); // Mock Logger

    public IPHService() {
        // Constructor for initialization if needed
    }

    /**
     * Guarda un registro IPH individual y registra la acción en la auditoría.
     * @param record El objeto IPHRecord a guardar.
     * @param usuario El nombre del usuario que realiza la acción.
     * @return true si la operación fue exitosa.
     * @throws ServiceException Si la inserción en la base de datos falla.
     */
    public boolean guardar(IPHRecord record, String usuario) throws ServiceException {
        record.setCapturadoPor(usuario);
        // Use ISO-8601 string representation
        record.setCreadoEn(LocalDateTime.now().toString());

        // El método insert en el DAO debería devolver true si es exitoso.
        boolean success = dao.insert(record);

        if (success) {
            // Log de auditoría solo si la inserción fue exitosa
            auditDAO.log(new AuditLog("CREAR", record.getNumeroInforme(), usuario, "Registro de IPH creado."));
        } else {
            // Lanza una excepción si el DAO indica un fallo (ej. violación de clave única)
            throw new ServiceException("Fallo al guardar el registro IPH: " + record.getNumeroInforme());
        }
        return success;
    }

    public List<IPHRecord> listarTodos() {
        return dao.findAll();
    }

    /**
     * Importa una lista de registros IPH, llamando a 'guardar' para cada uno.
     * Esta implementación usa transacciones separadas (una por 'guardar').
     * @param records La lista de IPHRecords a importar.
     * @param usuario El nombre del usuario que realiza la importación.
     * @throws ServiceException Si la importación se completa con errores, detallando el fallo.
     */
    public void importarLote(List<IPHRecord> records, String usuario) throws ServiceException {
        // En un sistema robusto, se preferiría IPHRecordDAO.batchInsert(records)
        // para una sola transacción atómica y mejor rendimiento.

        int total = records.size();
        int fallidos = 0;

        for (IPHRecord record : records) {
            try {
                // Reutilizamos el método guardar, que maneja la persistencia y el log de auditoría
                guardar(record, usuario);
            } catch (ServiceException e) {
                fallidos++;
                // Logueamos el error y continuamos con el siguiente registro
                log.error("Fallo la importación del informe " + record.getNumeroInforme() + ": " + e.getMessage());
            }
        }

        if (fallidos > 0) {
            // Log de auditoría para el evento de importación masiva con errores
            auditService.log("IMPORTAR", "", usuario,
                    "Importación completada con errores. Total: " + total + ", Fallidos: " + fallidos);

            throw new ServiceException("Importación completada. " + fallidos +
                    " registros fallaron debido a errores de datos o duplicados (Ver logs).");
        } else {
            // Log de auditoría para el evento de importación masiva exitosa
            auditService.log("IMPORTAR", "", usuario,
                    "Importación masiva exitosa. Total: " + total + " registros.");
        }
    }
}

// Minimal placeholder class for AuditService