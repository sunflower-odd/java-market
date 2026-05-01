package com.webmarket.util;

import org.flywaydb.core.Flyway;

public class DBBootstrap {
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/web_market?useUnicode=true&allowPublicKeyRetrieval=true";
    private static final String USER = "app_user";
    private static final String PASSWORD = "password";

    public static void init() {
        try {
            runMigrations();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // запускаем Flyway
    private static void runMigrations() {

        Flyway flyway = Flyway.configure()
                .dataSource(DB_URL, USER, PASSWORD)
                .load();

        flyway.migrate();
    }
}