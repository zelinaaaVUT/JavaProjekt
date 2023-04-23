package dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public final class DBConnection {
    private static volatile Connection dbConnection;

    private DBConnection() {}

    public static Connection getDbConnection(){
        if (dbConnection == null) {
            synchronized (DBConnection.class) {
                if (dbConnection == null) {
                    try {
                        Class.forName("org.sqlite.JDBC");
                        dbConnection = DriverManager.getConnection("jdbc:sqlite:src/db/film.db");
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return dbConnection;
    }
    public static void closeConnection() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
