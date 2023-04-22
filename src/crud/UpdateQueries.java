package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Film.Film;
import Film.FilmAnimated;
import dbconn.DBConnection;

public class UpdateQueries {
    public void UpdateFilmDB(List<Film> hraneFilmy){
        Connection conn = DBConnection.getDbConnection();
        String updateFilm = "UPDATE FilmHrany SET nazev = ?, reziser = ?, rok_vydani = ? WHERE ID_hrany = ?";
        String updateHerci = "UPDATE Herci SET jmeno = ? WHERE hrany_film_id = ?";

        for (Film f : hraneFilmy){
            if (f.isChanged()){
                try {
                    PreparedStatement prStmtFilm = conn.prepareStatement(updateFilm);
                    prStmtFilm.setString(1, f.getName());
                    prStmtFilm.setString(2, f.getDirector());
                    prStmtFilm.setInt(3, f.getRokVydani());
                    prStmtFilm.setInt(4, f.getSQLID());
                    prStmtFilm.executeUpdate();

                    PreparedStatement prStmtHerci = conn.prepareStatement(updateHerci);
                    prStmtHerci.setString(1, f.returnStaffName().toString());
                    prStmtHerci.setInt(2, f.getSQLID());
                    prStmtHerci.executeUpdate();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void UpdateFilmAnimatedDB(List<FilmAnimated> animovaneFilmy){
        Connection conn = DBConnection.getDbConnection();
        String updateFilmAnimated = "UPDATE FilmAnimovany SET nazev = ?, reziser = ?, rok_vydani = ?, doporuceny_vek = ? WHERE ID_animovany = ?";
        String updateAnimatori = "UPDATE Animatori SET jmeno = ? WHERE animovany_film_id = ?";

        for (FilmAnimated f : animovaneFilmy){
            if (f.isChanged()){
                try {
                    PreparedStatement prStmtFilm = conn.prepareStatement(updateFilmAnimated);
                    prStmtFilm.setString(1, f.getName());
                    prStmtFilm.setString(2, f.getDirector());
                    prStmtFilm.setInt(3, f.getRokVydani());
                    prStmtFilm.setInt(4, f.getMinVek());
                    prStmtFilm.setInt(5, f.getSQLID());
                    prStmtFilm.executeUpdate();

                    PreparedStatement prStmtAnimatori = conn.prepareStatement(updateAnimatori);
                    prStmtAnimatori.setString(1, f.returnStaffName().toString());
                    prStmtAnimatori.setInt(2, f.getSQLID());
                    prStmtAnimatori.executeUpdate();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
