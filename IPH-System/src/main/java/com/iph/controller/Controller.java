package com.iph.controller;

/**
 * Clase base abstracta para todos los controladores que proporciona utilidades base.
 * Soluciona: cannot find symbol class Controller en SearchIPHView.java
 */
public abstract class Controller {

    // Método base para obtener el usuario actual de la sesión.
    public String getUsuario() {
        return "usuario_actual_sesion";
    }
}