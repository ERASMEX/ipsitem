package com.iph.model;

import java.time.LocalDateTime;

public class AuditLog {
    private int id;
    private LocalDateTime timestamp;
    private String accion;
    private String tabla;
    private String usuario;
    private String detalles;
package com.iph.model;

import java.time.LocalDateTime;

    /**
     * Modelo para registro de auditoría
     * Soluciona: cannot find symbol getNumeroInforme(), getFechaHora(), getCambios()
     */
    public class AuditLog {
        private int id;
        private LocalDateTime timestamp;
        private String accion;
        private String tabla;
        private String usuario;
        private String detalles;

        // Constructor para inserción
        public AuditLog(String accion, String tabla, String usuario, String detalles) {
            this.accion = accion;
            this.tabla = tabla;
            this.usuario = usuario;
            this.detalles = detalles;
            this.timestamp = LocalDateTime.now();
        }

        // Constructor para mapeo de base de datos
        public AuditLog(int id, LocalDateTime timestamp, String accion, String tabla, String usuario, String detalles) {
            this.id = id;
            this.timestamp = timestamp;
            this.accion = accion;
            this.tabla = tabla;
            this.usuario = usuario;
            this.detalles = detalles;
        }

        // Getters originales
        public int getId() { return id; }
        public LocalDateTime getTimestamp() { return timestamp; }
        public String getAccion() { return accion; }
        public String getTabla() { return tabla; }
        public String getUsuario() { return usuario; }
        public String getDetalles() { return detalles; }

        // Getters alternativos requeridos por AdminPanelView.java
        public int getNumeroInforme() { return id; }
        public LocalDateTime getFechaHora() { return timestamp; }
        public String getCambios() { return detalles; }
    }
    // Constructor para inserción
    public AuditLog(String accion, String tabla, String usuario, String detalles) {
        this.accion = accion;
        this.tabla = tabla;
        this.usuario = usuario;
        this.detalles = detalles;
        this.timestamp = LocalDateTime.now();
    }

    // Constructor para mapeo de base de datos
    public AuditLog(int id, LocalDateTime timestamp, String accion, String tabla, String usuario, String detalles) {
        this.id = id;
        this.timestamp = timestamp;
        this.accion = accion;
        this.tabla = tabla;
        this.usuario = usuario;
        this.detalles = detalles;
    }

    // Getters
    public int getId() { return id; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getAccion() { return accion; }
    public String getTabla() { return tabla; }
    public String getUsuario() { return usuario; }
    public String getDetalles() { return detalles; }
}