package dataAccess.migrations;

import org.flywaydb.core.Flyway;


public class Init {
    public static void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/postgres",
                        "postgres", "postgres")
                .load();

        flyway.migrate();
    }
}
