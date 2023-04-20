package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dbconn.DBConnection;
import crud.SelectQueries;

public class InsertQueries {

    public void insertNewFilm(String nazev, String reziser, int rok_vydani, List<String> staff) {
        int filmID = 0;
        SelectQueries check = new SelectQueries();

        if (nazev == null || reziser == null || rok_vydani == 0)
            throw new NullPointerException("Name, reziser and rok vydaní must be set.");

        Connection conn = DBConnection.getDbConnection();

        String insertFilm = "INSERT INTO FilmHrany (nazev, reziser, rok_vydani) VALUES (?, ?, ?)";
        String insertHerci = "INSERT INTO Herci (jmeno, hrany_film_id) VALUES (?, ?)";

        try {
            if (check.CheckForDuplicateInsertMovie(conn, nazev, reziser, rok_vydani)){
                System.out.println("Duplikátní záznam filmu.");
            }else {
                PreparedStatement prStmt = conn.prepareStatement(insertFilm);
                prStmt.setString(1, nazev);
                prStmt.setString(2, reziser);
                prStmt.setInt(3, rok_vydani);

                prStmt.executeUpdate();
                System.out.println("Nový uživatel byl vložen do databáze!");

                try (var autoID = prStmt.getGeneratedKeys()){
                    if (autoID.next()){
                        filmID = autoID.getInt(1);
                    }else {
                        throw new SQLException("Failed to retrieve ID.");
                    }
                }
            }

            if (check.CheckForDuplicateInsertHerci(conn, staff.toString(), filmID)){
                System.out.println("Duplikátní záznam herců.");
            } else if (filmID == 0) {
                System.out.println("Duplikátní záznam herců.");
            } else {
                PreparedStatement prStmtHerci = conn.prepareStatement(insertHerci);
                prStmtHerci.setString(1, staff.toString());
                prStmtHerci.setInt(2, filmID);
                prStmtHerci.executeUpdate();
                System.out.println("Herci vloženi do databáze");
            }
        } catch (SQLException e) {
            System.out.println("Údaje už byly vloženy nebo jste zadali špatný SQL příkaz INSERT");
            e.printStackTrace();
        }
    }
    public void insertNewAnimatedFilm(String nazev, String reziser, int rok_vydani, int doporuceny_vek, List<String> staff) {
        int filmID = 0;
        SelectQueries check = new SelectQueries();

        if (nazev == null || reziser == null || rok_vydani == 0 || doporuceny_vek == 0)
            throw new NullPointerException("Name, reziser, rok vydaní, doporuceny vek must be set.");

        Connection conn = DBConnection.getDbConnection();

        String insertFilm = "INSERT INTO FilmAnimovany (nazev, reziser, rok_vydani, doporuceny_vek) VALUES (?, ?, ?, ?)";
        String insertHerci = "INSERT INTO Animatori (jmeno, animovany_film_id) VALUES (?, ?)";

        try {
            if (check.CheckForDuplicateInsertAnimatedMovie(conn, nazev, reziser, rok_vydani, doporuceny_vek)){
                System.out.println("Duplikátní záznam animovaného filmu.");
            }else {
                PreparedStatement prStmt = conn.prepareStatement(insertFilm);
                prStmt.setString(1, nazev);
                prStmt.setString(2, reziser);
                prStmt.setInt(3, rok_vydani);
                prStmt.setInt(4, doporuceny_vek);

                prStmt.executeUpdate();
                System.out.println("Nový uživatel byl vložen do databáze!");

                try (var autoID = prStmt.getGeneratedKeys()){
                    if (autoID.next()){
                        filmID = autoID.getInt(1);
                    }else {
                        throw new SQLException("Failed to retrieve ID.");
                    }
                }
            }

            if (check.CheckForDuplicateInsertAnimatori(conn, staff.toString(), filmID)){
                System.out.println("Duplikátní záznam animátorů.");
            } else if (filmID == 0) {
                System.out.println("Duplikátní záznam animátorů.");
            } else {
                PreparedStatement prStmtHerci = conn.prepareStatement(insertHerci);
                prStmtHerci.setString(1, staff.toString());
                prStmtHerci.setInt(2, filmID);
                prStmtHerci.executeUpdate();
                System.out.println("Animátoři vloženi do databáze");
            }
        } catch (SQLException e) {
            System.out.println("Údaje už byly vloženy nebo jste zadali špatný SQL příkaz INSERT");
            e.printStackTrace();
        }
    }
}
