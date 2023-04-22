package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Film.Film;
import Film.FilmAnimated;
import Managers.FilmManager;
import Recenze.RecenzeAnimated;
import Recenze.RecenzeLive;
import dbconn.DBConnection;
public class DeleteQueries {
    public void DeleteFilm(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy, List<Integer> toBeDeletedSQL_Live, List<Integer> toBeDeletedSQL_Animated){
        Connection conn = DBConnection.getDbConnection();
        String deleteFilm = "DELETE from FilmHrany WHERE ID_hrany = ?";
        String deleteHerci = "DELETE from Herci WHERE hrany_film_id = ?";
        String deleteRecenzeLive = "DELETE from RecenzeHrany WHERE hrany_film_id = ?";

        String deleteFilmAnimated = "DELETE from FilmAnimovany WHERE ID_animovany = ?";
        String deleteAnimatori = "DELETE from Animatori WHERE animovany_film_id = ?";
        String deleteRecenzeAnimated = "DELETE from RecenzeAnimovany WHERE animovany_film_id = ?";

        try {
            PreparedStatement prStmtRecenzeLive = conn.prepareStatement(deleteRecenzeLive);
            PreparedStatement prStmtHerci = conn.prepareStatement(deleteHerci);
            PreparedStatement prStmtFilm = conn.prepareStatement(deleteFilm);

            PreparedStatement prStmtRecenzeAnimated = conn.prepareStatement(deleteRecenzeAnimated);
            PreparedStatement prStmtAnimatori = conn.prepareStatement(deleteAnimatori);
            PreparedStatement prStmtFilmAnimated = conn.prepareStatement(deleteFilmAnimated);

            for (Integer ID : toBeDeletedSQL_Live){
                prStmtRecenzeLive.setInt(1, ID);
                prStmtRecenzeLive.executeUpdate();
                prStmtHerci.setInt(1, ID);
                prStmtHerci.executeUpdate();
                prStmtFilm.setInt(1, ID);
                prStmtFilm.executeUpdate();
            }

            for (Integer ID : toBeDeletedSQL_Animated){
                prStmtRecenzeAnimated.setInt(1, ID);
                prStmtRecenzeAnimated.executeUpdate();
                prStmtAnimatori.setInt(1, ID);
                prStmtAnimatori.executeUpdate();
                prStmtFilmAnimated.setInt(1, ID);
                prStmtFilmAnimated.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
