// --- [File: src/main/java/com/iph/util/ValidationUtil.java] ---
package com.iph.util;

public class ValidationUtil {

    private static final String COORD_REGEX =
            "^-?\\d{1,2}\\.\\d{1,6},\\s*-?\\d{1,3}\\.\\d{1,6}$";

    public static boolean esCoordenadaValida(String coords) {
        if (coords == null || coords.trim().isEmpty()) return false;
        if (!coords.matches(COORD_REGEX)) return false;

        try {
            String[] parts = coords.split(",");
            double lat = Double.parseDouble(parts[0].trim());
            double lon = Double.parseDouble(parts[1].trim());

            return lat >= -90.0 && lat <= 90.0 && lon >= -180.0 && lon <= 180.0;
        } catch (Exception e) {
            return false;
        }
    }

    public static String formatearCoordenadas(String coords) {
        if (!esCoordenadaValida(coords)) return coords;
        String[] parts = coords.replaceAll("\\s", "").split(",");
        return String.format("%.6f, %.6f",
                Double.parseDouble(parts[0]),
                Double.parseDouble(parts[1])
        );
    }
}