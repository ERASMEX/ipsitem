// --- [File: src/main/java/com/iph/model/Vehiculo.java] ---
package com.iph.model;

public class Vehiculo {
    private String tipo, marca, placa, serie;
    public Vehiculo() {}
    public Vehiculo(String tipo, String marca, String placa, String serie) {
        this.tipo = tipo; this.marca = marca; this.placa = placa; this.serie = serie;
    }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }
}