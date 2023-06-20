package util;

import config.DatabaseConfig;

import java.sql.Connection;

public class ApplicationContext {
    public static final Connection CONNECTION;

    static {
        CONNECTION = new DatabaseConfig().getCreatedConnection();
    }
}
