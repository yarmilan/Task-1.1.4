package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private static final String DB_LOG = "postgres";
    private static final String DB_PASS = "Publish1996";
    public static final String DB_DRIVER_CLASS = "org.postgresql.Driver";
    public static final String DB_DIALECT = "org.hibernate.dialect.PostgreSQLDialect";

    public static Connection getconn() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_LOG, DB_PASS);
    }

}
