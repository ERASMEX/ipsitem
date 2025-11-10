package com.iph.model;

/**
 * Modelo mínimo para que DashboardController y RegisterIPHView compilen
 */
public class IPHRecord {

    private int id;
    private String folio;

    public IPHRecord() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFolio() { return folio; }
    public void setFolio(String folio) { this.folio = folio; }
    package com.iph.model;

import java.util.List;

    /**
     * Modelo completo para el registro de IPH, incluyendo todos los campos referenciados en DAOs y Services.
     */
    public class IPHRecord {

        private int id;
        private String numeroInforme; // getNumeroInforme
        private String fechaHechos; // getFechaHechos
        private String denunciante; // getDenunciante
        private String tipoHecho; // getTipoHecho
        private String tipoDelito; // getTipoDelito
        private String lugar; // getLugar
        private String autoridad; // getAutoridad
        private String puestaDisposicion; // getPuestaDisposicion
        private String victima; // getVictima
        private String coordenadas; // getCoordenadas
        private String estadoProcesal; // getEstadoProcesal
        private String observaciones; // getObservaciones
        private String capturadoPor; // setCapturadoPor / getCapturadoPor
        private String creadoEn; // setCreadoEn / getCreadoEn

        // Asumo que son Listas de Strings (puede ser necesario List<ModeloDetenido> en el futuro)
        private List<String> detenidos; // getDetenidos
        private List<String> vehiculos; // getVehiculos

        public IPHRecord() {}

        // Getters y Setters

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getNumeroInforme() { return numeroInforme; }
        public void setNumeroInforme(String numeroInforme) { this.numeroInforme = numeroInforme; }

        public String getFechaHechos() { return fechaHechos; }
        public void setFechaHechos(String fechaHechos) { this.fechaHechos = fechaHechos; }

        public String getDenunciante() { return denunciante; }
        public void setDenunciante(String denunciante) { this.denunciante = denunciante; }

        public String getTipoHecho() { return tipoHecho; }
        public void setTipoHecho(String tipoHecho) { this.tipoHecho = tipoHecho; }

        public String getTipoDelito() { return tipoDelito; }
        public void setTipoDelito(String tipoDelito) { this.tipoDelito = tipoDelito; }

        public String getLugar() { return lugar; }
        public void setLugar(String lugar) { this.lugar = lugar; }

        public String getAutoridad() { return autoridad; }
        public void setAutoridad(String autoridad) { this.autoridad = autoridad; }

        public String getPuestaDisposicion() { return puestaDisposicion; }
        public void setPuestaDisposicion(String puestaDisposicion) { this.puestaDisposicion = puestaDisposicion; }

        public String getVictima() { return victima; }
        public void setVictima(String victima) { this.victima = victima; }

        public String getCoordenadas() { return coordenadas; }
        public void setCoordenadas(String coordenadas) { this.coordenadas = coordenadas; }

        public String getEstadoProcesal() { return estadoProcesal; }
        public void setEstadoProcesal(String estadoProcesal) { this.estadoProcesal = estadoProcesal; }

        public String getObservaciones() { return observaciones; }
        public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

        public String getCapturadoPor() { return capturadoPor; }
        public void setCapturadoPor(String capturadoPor) { this.capturadoPor = capturadoPor; } // Soluciona setCapturadoPor

        public String getCreadoEn() { return creadoEn; }
        public void setCreadoEn(String creadoEn) { this.creadoEn = creadoEn; } // Soluciona setCreadoEn

        public List<String> getDetenidos() { return detenidos; }
        public void setDetenidos(List<String> detenidos) { this.detenidos = detenidos; } // Soluciona setDetenidos

        public List<String> getVehiculos() { return vehiculos; }
        public void setVehiculos(List<String> vehiculos) { this.vehiculos = vehiculos; } // Soluciona setVehiculos
    }
}