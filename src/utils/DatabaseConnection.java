package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {
    private static DatabaseConnection instance;

    public static synchronized DatabaseConnection GetInstance() {
        if (instance == null) instance =  new DatabaseConnection();
        return instance;
    }

    private static final String URL = "jdbc:mariadb://localhost:3306/bank?allowMultiQueries=true";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "fms2025";
    private Connection connection;

    private DatabaseConnection() {
        try {
            this.connection = connection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connection() throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }

    public Connection getConnection() {
        return this.connection;
    }
}
