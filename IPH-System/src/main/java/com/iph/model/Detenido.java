// --- [File: src/main/java/com/iph/model/Detenido.java] ---
package com.iph.model;

public class Detenido {
    private String nombre, sexo;
    public Detenido() {}
    public Detenido(String nombre, String sexo) { this.nombre = nombre; this.sexo = sexo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
}