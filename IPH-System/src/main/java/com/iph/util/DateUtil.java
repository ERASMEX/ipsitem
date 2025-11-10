package com.iph.util;

import org.apache.poi.ss.usermodel.DateUtil;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Utilidad para manejo de fechas.
 */
public class DateUtil {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    // Método para Excel
    public static Date getJavaDate(double value) {
        return DateUtil.getJavaDate(value);
    }

    // Método requerido por RegisterIPHView.java
    public static String formatDate(Date date) {
        if (date == null) return "";
        return DATE_FORMAT.format(date);
    }
}