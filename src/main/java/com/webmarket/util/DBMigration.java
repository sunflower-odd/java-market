package com.webmarket.util;

import org.flywaydb.core.Flyway;

public class DBMigration {

    public static void migrate() {

        Flyway flyway = Flyway.configure()
                .dataSource(
                        "jdbc:mysql://localhost:3306/web_market?useUnicode=true&allowPublicKeyRetrieval=true",
                        "app_user",
                        "password"
                )
                .load();

        flyway.migrate();
    }
}