package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Film.Film;
import Film.FilmAnimated;
import Managers.FilmManager;
import dbconn.DBConnection;

public class SelectQueries {
    public boolean CheckForDuplicateInsertMovie(Connection conn, String nazev, String reziser, int rok_vydani){
        String selectFilm = "SELECT ID_hrany FROM FilmHrany WHERE nazev = ? AND reziser = ? AND rok_vydani = ?";
        boolean isDuplicate = false;

        try {
            PreparedStatement prStmt = conn.prepareStatement(selectFilm);
            prStmt.setString(1, nazev);
            prStmt.setString(2, reziser);
            prStmt.setInt(3, rok_vydani);

            ResultSet rs = prStmt.executeQuery();

            if (rs.next()){
                isDuplicate = true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return isDuplicate;
    }
    public boolean CheckForDuplicateInsertAnimatedMovie(Connection conn, String nazev, String reziser, int rok_vydani, int doporuceny_vek){
        String selectFilm = "SELECT ID_animovany FROM FilmAnimovany WHERE nazev = ? AND reziser = ? AND rok_vydani = ? AND doporuceny_vek = ?";
        boolean isDuplicate = false;

        try {
            PreparedStatement prStmt = conn.prepareStatement(selectFilm);
            prStmt.setString(1, nazev);
            prStmt.setString(2, reziser);
            prStmt.setInt(3, rok_vydani);
            prStmt.setInt(4, doporuceny_vek);

            ResultSet rs = prStmt.executeQuery();

            if (rs.next()){
                isDuplicate = true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return isDuplicate;
    }
    public boolean CheckForDuplicateInsertHerci(Connection conn, String jmeno, int hrany_film_id){
        String selectHerci = "SELECT ID_herci FROM Herci WHERE jmeno = ? AND hrany_film_id = ?";
        boolean isDuplicate = false;

        try {
            PreparedStatement prStmt = conn.prepareStatement(selectHerci);
            prStmt.setString(1, jmeno);
            prStmt.setInt(2, hrany_film_id);

            ResultSet rs = prStmt.executeQuery();

            if (rs.next()){
                isDuplicate = true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return isDuplicate;
    }
    public boolean CheckForDuplicateInsertAnimatori(Connection conn, String jmeno, int animovany_film_id){
        String selectAnimatori = "SELECT ID_animator FROM Animatori WHERE jmeno = ? AND animovany_film_id = ?";
        boolean isDuplicate = false;

        try {
            PreparedStatement prStmt = conn.prepareStatement(selectAnimatori);
            prStmt.setString(1, jmeno);
            prStmt.setInt(2, animovany_film_id);

            ResultSet rs = prStmt.executeQuery();

            if (rs.next()){
                isDuplicate = true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return isDuplicate;
    }

    public void LoadFilmFromDB (List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy){
        int filmID;
        String herci;
        Connection conn = DBConnection.getDbConnection();
        String selectAll = "SELECT ID_hrany, nazev, reziser, rok_vydani FROM FilmHrany";
        String selectHerci = "SELECT jmeno FROM Herci WHERE hrany_film_id = ?";

        try {
            PreparedStatement prStmt = conn.prepareStatement(selectAll);
            ResultSet rs = prStmt.executeQuery();

            while (rs.next()){
                Film film = new Film(rs.getString("nazev"), rs.getString("reziser"), rs.getInt("rok_vydani"));
                hraneFilmy.add(film);
                filmID = rs.getInt("ID_hrany");

                PreparedStatement prStmtHerci = conn.prepareStatement(selectHerci);
                prStmtHerci.setInt(1, filmID);
                ResultSet rsHerci = prStmtHerci.executeQuery();
                if (rs.next()){
                    herci = rs.getString("jmeno");
                }
                //mám string herci který vypadá takto: [Petr, Tom, Franta] ... dodělat přidání herců k filmu

            }
        }catch (SQLException e){
            e.printStackTrace();
        }


    }
}
