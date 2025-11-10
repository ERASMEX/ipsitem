package com.iph.util;

import javax.swing.ImageIcon;

/**
 * Utilidad para constantes de iconos.
 * Soluciona: cannot find symbol variable MAP/BACKUP en DashboardView.java
 */
public class IconUtil {
    // NOTA: Asegúrate de que los archivos 'map.png' y 'backup.png' existan
    // en tu carpeta 'src/main/resources/icons/'
    public static final ImageIcon MAP = new ImageIcon(IconUtil.class.getResource("/icons/map.png"));
    public static final ImageIcon BACKUP = new ImageIcon(IconUtil.class.getResource("/icons/backup.png"));
}