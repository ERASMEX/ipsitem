package com.iph.model;

public class User {
    private int id;
    private String username;
    private String email;
    private String rol;
    private String password;
    // Asumo que tienes otros campos como activo, last_login, etc.

    public User() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // Método getUsuario() requerido por compatibilidad con DAO/Vistas
    public String getUsuario() {
        return username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}