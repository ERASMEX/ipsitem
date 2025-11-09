// --- [File: src/main/java/com/iph/model/IPHRecord.java] ---
package com.iph.model;

import java.util.List;

public class IPHRecord {
    private Integer id;
    private String numeroInforme, fechaHechos, denunciante, tipoHecho, tipoDelito;
    private String lugar, autoridad, puestaDisposicion, victima, coordenadas;
    private String estadoProcesal, observaciones, capturadoPor, creadoEn;
    private List<Detenido> detenidos;
    private List<Vehiculo> vehiculos;

    public IPHRecord() {}

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
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
    public List<Detenido> getDetenidos() { return detenidos; }
    public void setDetenidos(List<Detenido> detenidos) { this.detenidos = detenidos; }
    public List<Vehiculo> getVehiculos() { return vehiculos; }
    public void setVehiculos(List<Vehiculo> vehiculos) { this.vehiculos = vehiculos; }
    public String getVictima() { return victima; }
    public void setVictima(String victima) { this.victima = victima; }
    public String getCoordenadas() { return coordenadas; }
    public void setCoordenadas(String coordenadas) { this.coordenadas = coordenadas; }
    public String getEstadoProcesal() { return estadoProcesal; }
    public void setEstadoProcesal(String estadoProcesal) { this.estadoProcesal = estadoProcesal; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public String getCapturadoPor() { return capturadoPor; }
    public void setCapturadoPor(String capturadoPor) { this.capturadoPor = capturadoPor; }
    public String getCreadoEn() { return creadoEn; }
    public void setCreadoEn(String creadoEn) { this.creadoEn = creadoEn; }
}