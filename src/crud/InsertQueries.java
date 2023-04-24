package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Film.Film;
import Film.FilmAnimated;
import Recenze.Recenze;
import Recenze.RecenzeLive;
import Recenze.RecenzeAnimated;
import dbconn.DBConnection;
import crud.SelectQueries;

public class InsertQueries {

    public void insertNewFilm(String nazev, String reziser, int rok_vydani, List<String> staff) {
        int filmID = 0;
        SelectQueries check = new SelectQueries();

        if (nazev == null || reziser == null || rok_vydani == 0)
            throw new NullPointerException("Jméno, režisér a rok vydání musí být nastaveny.");

        Connection conn = DBConnection.getDbConnection();

        String insertFilm = "INSERT INTO FilmHrany (nazev, reziser, rok_vydani) VALUES (?, ?, ?)";
        String insertHerci = "INSERT INTO Herci (jmeno, hrany_film_id) VALUES (?, ?)";

        try {
            if (check.CheckForDuplicateInsertMovie(conn, nazev, reziser, rok_vydani)){
            }else {
                PreparedStatement prStmt = conn.prepareStatement(insertFilm);
                prStmt.setString(1, nazev);
                prStmt.setString(2, reziser);
                prStmt.setInt(3, rok_vydani);

                prStmt.executeUpdate();

                try (var autoID = prStmt.getGeneratedKeys()){
                    if (autoID.next()){
                        filmID = autoID.getInt(1);
                    }else {
                        throw new SQLException("Failed to retrieve ID.");
                    }
                }
            }

            if (check.CheckForDuplicateInsertHerci(conn, staff.toString(), filmID)){
            } else if (filmID == 0) {
            } else {
                PreparedStatement prStmtHerci = conn.prepareStatement(insertHerci);
                prStmtHerci.setString(1, staff.toString());
                prStmtHerci.setInt(2, filmID);
                prStmtHerci.executeUpdate();
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
            throw new NullPointerException("Jméno, režisér, rok vydaní a doporučený věk musí být nastaveny.");

        Connection conn = DBConnection.getDbConnection();

        String insertFilm = "INSERT INTO FilmAnimovany (nazev, reziser, rok_vydani, doporuceny_vek) VALUES (?, ?, ?, ?)";
        String insertHerci = "INSERT INTO Animatori (jmeno, animovany_film_id) VALUES (?, ?)";

        try {
            if (check.CheckForDuplicateInsertAnimatedMovie(conn, nazev, reziser, rok_vydani, doporuceny_vek)){
            }else {
                PreparedStatement prStmt = conn.prepareStatement(insertFilm);
                prStmt.setString(1, nazev);
                prStmt.setString(2, reziser);
                prStmt.setInt(3, rok_vydani);
                prStmt.setInt(4, doporuceny_vek);

                prStmt.executeUpdate();

                try (var autoID = prStmt.getGeneratedKeys()){
                    if (autoID.next()){
                        filmID = autoID.getInt(1);
                    }else {
                        throw new SQLException("Failed to retrieve ID.");
                    }
                }
            }

            if (check.CheckForDuplicateInsertAnimatori(conn, staff.toString(), filmID)){
            } else if (filmID == 0) {
            } else {
                PreparedStatement prStmtHerci = conn.prepareStatement(insertHerci);
                prStmtHerci.setString(1, staff.toString());
                prStmtHerci.setInt(2, filmID);
                prStmtHerci.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Údaje už byly vloženy nebo jste zadali špatný SQL příkaz INSERT");
            e.printStackTrace();
        }
    }

    public void InsertReviewFilm(List<Film> hraneFilmy){
        int filmID = 0, rok_vydani;
        String nazev, reziser;
        Connection conn = DBConnection.getDbConnection();
        SelectQueries check = new SelectQueries();

        String insertRecenze = "INSERT INTO RecenzeHrany (jmeno, komentar, hodnoceni, hrany_film_id) VALUES (?, ?, ?, ?)";
        String selectFilmID = "SELECT ID_hrany FROM FilmHrany WHERE nazev = ? AND reziser = ? AND rok_vydani = ?";

        try {
            for (Film f : hraneFilmy){
                List<Recenze> recenze = f.getRecenze();
                nazev = f.getName();
                reziser = f.getDirector();
                rok_vydani = f.getRokVydani();

                PreparedStatement prStmt = conn.prepareStatement(selectFilmID);
                prStmt.setString(1, nazev);
                prStmt.setString(2, reziser);
                prStmt.setInt(3, rok_vydani);
                ResultSet rs = prStmt.executeQuery();

                if (rs.next()){
                    filmID = rs.getInt("ID_hrany");
                }

                PreparedStatement prStmtRecenze = conn.prepareStatement(insertRecenze);
                for (Recenze a : recenze){
                    if (check.CheckForDuplicateInsertReviewLive(conn, a.getJmenoDivaka(), a.getKomentar(), a.getHodnoceni(), filmID)){
                    }else {
                        prStmtRecenze.setString(1, a.getJmenoDivaka());
                        prStmtRecenze.setString(2, a.getKomentar());
                        prStmtRecenze.setString(3, a.getHodnoceni());
                        prStmtRecenze.setInt(4, filmID);
                        prStmtRecenze.executeUpdate();
                    }
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void InsertReviewFilmAnimated(List<FilmAnimated> animovanyFilmy){
        int filmID = 0, rok_vydani, doporuceny_vek;
        String nazev, reziser;
        Connection conn = DBConnection.getDbConnection();
        SelectQueries check = new SelectQueries();

        String insertRecenze = "INSERT INTO RecenzeAnimovany (jmeno, komentar, hodnoceni, animovany_film_id) VALUES (?, ?, ?, ?)";
        String selectFilmID = "SELECT ID_animovany FROM FilmAnimovany WHERE nazev = ? AND reziser = ? AND rok_vydani = ? AND doporuceny_vek = ?";

        try {
            for (FilmAnimated f : animovanyFilmy){
                List<Recenze> recenze = f.getRecenze();
                nazev = f.getName();
                reziser = f.getDirector();
                rok_vydani = f.getRokVydani();
                doporuceny_vek = f.getMinVek();

                PreparedStatement prStmt = conn.prepareStatement(selectFilmID);
                prStmt.setString(1, nazev);
                prStmt.setString(2, reziser);
                prStmt.setInt(3, rok_vydani);
                prStmt.setInt(4, doporuceny_vek);
                ResultSet rs = prStmt.executeQuery();

                if (rs.next()){
                    filmID = rs.getInt("ID_animovany");
                }

                PreparedStatement prStmtRecenze = conn.prepareStatement(insertRecenze);
                for (Recenze a : recenze){
                    if (check.CheckForDuplicateInsertReviewAnimated(conn, a.getJmenoDivaka(), a.getKomentar(), a.getHodnoceni(), filmID)){
                    }else {
                        prStmtRecenze.setString(1, a.getJmenoDivaka());
                        prStmtRecenze.setString(2, a.getKomentar());
                        prStmtRecenze.setString(3, a.getHodnoceni());
                        prStmtRecenze.setInt(4, filmID);
                        prStmtRecenze.executeUpdate();
                    }
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
