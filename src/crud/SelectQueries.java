package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Film.Film;
import Film.FilmAnimated;
import Recenze.RecenzeAnimated;
import Recenze.RecenzeLive;
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

    public void LoadFilmFromDB (List<Film> hraneFilmy){
        int filmID;
        String herci;
        Connection conn = DBConnection.getDbConnection();
        String selectAll = "SELECT ID_hrany, nazev, reziser, rok_vydani FROM FilmHrany";
        String selectHerci = "SELECT jmeno FROM Herci WHERE hrany_film_id = ?";
        String selectRecenze = "SELECT jmeno, komentar, hodnoceni FROM RecenzeHrany WHERE hrany_film_id = ?";

        try {
            PreparedStatement prStmt = conn.prepareStatement(selectAll);
            ResultSet rs = prStmt.executeQuery();

            while (rs.next()){
                Film film = new Film(rs.getString("nazev"), rs.getString("reziser"), rs.getInt("rok_vydani"));
                hraneFilmy.add(film);
                filmID = rs.getInt("ID_hrany");
                film.setSQLID(filmID);

                PreparedStatement prStmtHerci = conn.prepareStatement(selectHerci);
                prStmtHerci.setInt(1, filmID);
                ResultSet rsHerci = prStmtHerci.executeQuery();
                if (rsHerci.next()){
                    herci = rsHerci.getString("jmeno");
                    String[] staffSplit = (herci.substring(1, herci.length() - 1)).split(", "); //removes brackets, split by comma and put it in array
                    for (String herec : staffSplit){
                        film.addStaff(herec);
                    }
                }
                PreparedStatement prStmtRecenze = conn.prepareStatement(selectRecenze);
                prStmtRecenze.setInt(1, filmID);
                ResultSet rsRecenze = prStmtRecenze.executeQuery();
                while (rsRecenze.next()){
                    film.addRecenze(new RecenzeLive(rsRecenze.getString("jmeno"), rsRecenze.getString("komentar"), rsRecenze.getString("hodnoceni")));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void LoadAnimatedFilmFromDB (List<FilmAnimated> animovaneFilmy){
        int filmID;
        String animatori;
        Connection conn = DBConnection.getDbConnection();
        String selectAll = "SELECT ID_animovany, nazev, reziser, rok_vydani, doporuceny_vek FROM FilmAnimovany";
        String selectAnimatori = "SELECT jmeno FROM Animatori WHERE animovany_film_id = ?";
        String selectRecenze = "SELECT jmeno, komentar, hodnoceni FROM RecenzeAnimovany WHERE animovany_film_id = ?";


        try {
            PreparedStatement prStmt = conn.prepareStatement(selectAll);
            ResultSet rs = prStmt.executeQuery();

            while (rs.next()){
                FilmAnimated film = new FilmAnimated(rs.getString("nazev"), rs.getString("reziser"), rs.getInt("rok_vydani"), rs.getInt("doporuceny_vek"));
                animovaneFilmy.add(film);
                filmID = rs.getInt("ID_animovany");
                film.setSQLID(filmID);

                PreparedStatement prStmtHerci = conn.prepareStatement(selectAnimatori);
                prStmtHerci.setInt(1, filmID);
                ResultSet rsAnimatori = prStmtHerci.executeQuery();
                if (rsAnimatori.next()){
                    animatori = rsAnimatori.getString("jmeno");
                    String[] staffSplit = (animatori.substring(1, animatori.length() - 1)).split(", "); //removes brackets, split by comma and put it in array
                    for (String animator : staffSplit){
                        film.addStaff(animator);
                    }
                }

                PreparedStatement prStmtRecenze = conn.prepareStatement(selectRecenze);
                prStmtRecenze.setInt(1, filmID);
                ResultSet rsRecenze = prStmtRecenze.executeQuery();
                while (rsRecenze.next()){
                    film.addRecenze(new RecenzeAnimated(rsRecenze.getString("jmeno"), rsRecenze.getString("komentar"), rsRecenze.getString("hodnoceni")));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean CheckForDuplicateInsertReviewLive(Connection conn, String jmeno, String komentar, String hodnoceni, int hrany_film_id){
        String selectRecenze = "SELECT ID_recenze FROM RecenzeHrany WHERE jmeno = ? AND komentar = ? AND hodnoceni = ? AND hrany_film_id = ?";
        boolean isDuplicate = false;

        try {
            PreparedStatement prStmt = conn.prepareStatement(selectRecenze);
            prStmt.setString(1, jmeno);
            prStmt.setString(2, komentar);
            prStmt.setString(3, hodnoceni);
            prStmt.setInt(4, hrany_film_id);

            ResultSet rs = prStmt.executeQuery();

            if (rs.next()){
                isDuplicate = true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return isDuplicate;
    }
    public boolean CheckForDuplicateInsertReviewAnimated(Connection conn, String jmeno, String komentar, String hodnoceni, int animovany_film_id){
        String selectRecenze = "SELECT ID_recenze FROM RecenzeAnimovany WHERE jmeno = ? AND komentar = ? AND hodnoceni = ? AND animovany_film_id = ?";
        boolean isDuplicate = false;

        try {
            PreparedStatement prStmt = conn.prepareStatement(selectRecenze);
            prStmt.setString(1, jmeno);
            prStmt.setString(2, komentar);
            prStmt.setString(3, hodnoceni);
            prStmt.setInt(4, animovany_film_id);

            ResultSet rs = prStmt.executeQuery();

            if (rs.next()){
                isDuplicate = true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return isDuplicate;
    }
}
