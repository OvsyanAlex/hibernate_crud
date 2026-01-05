package org.example.util;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LiquibaseRunner {
    private static final String DB_URL = PropertiesUtil.getProperties("db.url");
    private static final String DB_USER = PropertiesUtil.getProperties("db.user");
    private static final String DB_PASSWORD = PropertiesUtil.getProperties("db.password");


    static {
        try (Connection connectionForLiquibase = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connectionForLiquibase))) {
            Liquibase liquibase = new Liquibase("db/changelog/changelog-label-master.yaml", new ClassLoaderResourceAccessor(), database);
            liquibase.update();
            System.err.println("liquibase are updated");
        } catch (LiquibaseException | SQLException e) {
            throw new RuntimeException("Liquibase was not execute", e);
        }
    }
}
