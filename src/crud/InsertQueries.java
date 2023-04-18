package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dbconn.DBConnection;

public class InsertQueries {

    public void performInsertQuery(String insertQuery) {
        if (insertQuery == null) {
            throw new NullPointerException("query must not be null!");
        } else if (insertQuery.isEmpty()) {
            throw new IllegalArgumentException("query must not be empty!");
        }
        Connection conn = DBConnection.getDbConnection();
        try (PreparedStatement prStmt = conn.prepareStatement(insertQuery);) {
            int rowsInserted = prStmt.executeUpdate();
            System.out.println("uspech");
        } catch (SQLException e) {
            System.out.println("uspech ale uziv už byl vložen");
            // e.printStackTrace();
        }
    }

    public static void insertNewUser(String email, String name, String surname) {
        if (email == null || name == null || surname == null)
            throw new NullPointerException("Email, name and surname must be set.");

        Connection conn = DBConnection.getDbConnection();

        String insertUser = "INSERT INTO USER " + "(email, name, surname)" + " VALUES(?, ?, ?)";

        try (PreparedStatement prStmt = conn.prepareStatement(insertUser)) {
            prStmt.setString(1, email);
            prStmt.setString(2, name);
            prStmt.setString(3, surname);

            prStmt.executeUpdate();
            System.out.println("Nový uživatel byl vložen do databáze!");
        } catch (SQLException e) {
            System.out.println("Uživatel už byl vložen nebo jste zadali špatný SQL příkaz INSERT");
            // e.printStackTrace();
        }
    }
}
