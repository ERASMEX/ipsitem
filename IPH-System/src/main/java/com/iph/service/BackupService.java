// --- [File: src/main/java/com/iph/service/BackupService.java] ---
package com.iph.service;

import com.iph.config.AppConfig;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.format.DateTimeFormatter;

public class BackupService {

    public static String crearBackup() throws Exception {
        String dbName = AppConfig.get("db.name");
        String backupDir = AppConfig.get("db.backup.path");

        File dir = new File(backupDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String timestamp = java.time.LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        Path source = Paths.get(dbName);
        Path target = Paths.get(backupDir + "iph_backup_" + timestamp + ".db");

        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        return target.toAbsolutePath().toString();
    }
}