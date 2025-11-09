package com.iph.controller;

/**
 * Clase base abstracta para todos los controladores
 * Soluciona: cannot find symbol class Controller en SearchIPHView.java
 */
public abstract class Controller {

    // Necesario para SearchIPHView
    public String getUsuario() {
        // En una aplicación real, esto se obtendría del estado de la sesión
        return "usuario_actual_sesion";
    }
}