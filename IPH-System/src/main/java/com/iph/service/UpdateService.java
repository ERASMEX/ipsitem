package com.iph.service;

import com.iph.config.AppConfig;
import com.iph.util.LoggerUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateService {
    private static final org.apache.logging.log4j.Logger log = LoggerUtil.getLogger(UpdateService.class);
    private static final String CURRENT_VERSION = AppConfig.get("app.version"); // Definido en config.properties

    /**
     * Simula la comprobación de una versión más reciente en una ubicación central (LAN/Web).
     * En un entorno real, esto apuntaría a un archivo de texto en un servidor de red local.
     */
    public static String checkForUpdates() {
        String remoteUrl = AppConfig.get("update.check.url");
        if (remoteUrl.isEmpty()) return null;

        try {
            // Ejemplo: leer la versión de un archivo de texto simple en una URL local o de red.
            URL url = new URL(remoteUrl);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String latestVersion = reader.readLine().trim();

                if (!latestVersion.equals(CURRENT_VERSION)) {
                    log.info("Nueva versión disponible: " + latestVersion);
                    return latestVersion;
                }
            }
        } catch (Exception e) {
            log.error("Error al comprobar la versión remota. URL: " + remoteUrl, e);
        }
        return null; // Versión actual o error.
    }

    /**
     * Retorna la versión actual.
     */
    public static String getCurrentVersion() {
        return CURRENT_VERSION;
    }
}