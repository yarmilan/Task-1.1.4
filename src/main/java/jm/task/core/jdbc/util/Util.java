package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private static final String DB_LOG = "postgres";
    private static final String DB_PASS = "Publish1996";

    public static Connection getconn() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_LOG, DB_PASS);
    }
}
