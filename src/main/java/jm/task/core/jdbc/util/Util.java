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
    public static SessionFactory getSessionFactory () {
        Properties properties = new Properties();
        properties.setProperty("hibernate.driver_class", DB_DRIVER_CLASS);
        properties.setProperty("hibernate.connection.url", DB_URL);
        properties.setProperty("hibernate.connection.username", DB_LOG);
        properties.setProperty("hibernate.connection.password", DB_PASS);
        properties.setProperty("hibernate.dialect", DB_DIALECT);

        Configuration configuration = new Configuration().addAnnotatedClass(User.class).setProperties(properties);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
