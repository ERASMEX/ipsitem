// --- [File: src/main/java/com/iph/dao/BaseDAO.java] ---
package com.iph.dao;

import com.iph.config.DatabaseConfig;
import java.sql.Connection;

public abstract class BaseDAO {
    protected Connection getConnection() throws Exception {
        return DatabaseConfig.getConnection();
    }

    // Este método no se usaba, try-with-resources es preferible.
    // protected void closeResources(AutoCloseable... resources) { ... }
}