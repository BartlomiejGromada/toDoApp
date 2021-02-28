package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SQLiteJDBC {

    private static volatile Connection instance;
    private static final String databaseName = "jdbc:sqlite:toToDatabase.db";

    public static Connection getInstance() {

        Connection result = instance;

        if (result != null)
            return result;

        synchronized (Connection.class) {
            if (instance == null) {
                try {
                    instance = DriverManager.getConnection(databaseName);
                } catch (SQLException e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
            }

            return instance;
        }
    }

    public static void close() throws SQLException {
        getInstance().close();
    }
}
