package com.iph.util;

import javax.swing.ImageIcon;

/**
 * Utilidad para constantes de iconos
 * Soluciona: cannot find symbol variable MAP/BACKUP en DashboardView.java
 */
public class IconUtil {
    // Sustituye "icons/map.png" con la ruta real dentro de tus recursos si es diferente
    public static final ImageIcon MAP = new ImageIcon(IconUtil.class.getResource("/icons/map.png"));
    public static final ImageIcon BACKUP = new ImageIcon(IconUtil.class.getResource("/icons/backup.png"));

    // Nota: Asegúrate de que las rutas '/icons/...' existan en tu src/main/resources
}