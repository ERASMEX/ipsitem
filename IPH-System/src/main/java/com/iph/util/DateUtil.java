package com.iph.util;

import org.apache.poi.ss.usermodel.DateUtil;
import java.util.Date;
import java.text.SimpleDateFormat; // Requerido para formatDate

/**
 * Utilidad para manejo de fechas
 * Soluciona: Duplicidad (asegúrate de que solo exista este archivo) y falta de formatDate
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