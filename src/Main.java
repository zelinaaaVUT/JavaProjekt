import Film.Film;
import Film.FilmAnimated;
import Managers.FileManager;
import Managers.FilmManager;
import Managers.ReviewManager;
import Managers.StaffManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import crud.DeleteQueries;
import crud.SelectQueries;
import crud.UpdateQueries;
import dbconn.DBConnection;
import crud.InsertQueries;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        boolean run = true;
        int input;
        List<Film> hraneFilmy = new ArrayList<>();
        List<FilmAnimated> animovaneFilmy = new ArrayList<>();
        List<Integer> toBeDeletedSQL_Live = new ArrayList<>();
        List<Integer> toBeDeletedSQL_Animated = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        NacteniZSQL(hraneFilmy, animovaneFilmy);
        do {
            System.out.println("\n1 - přidání filmu\n2 - vymazání filmu\n3 - editnutí filmu\n4 - výpis všech filmu\n5 - přídání recenze\n6 - vyhledání filmu\n" +
                    "7 - výpis herců, kteří se podíleli na více filmech\n8 - výpis animátorů, kteří se podíleli na více filmech\n" +
                    "9 - výpis filmů, na kterých se herec podílel\n10 - uložení do souboru\n11 - načtení ze souboru\n12 - ukončení programu");
            input = sc.nextInt();
            switch (input) {
                case 1 -> {
                    try {
                        FilmManager.Pridani(hraneFilmy, animovaneFilmy);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                case 2 -> {
                    String name;
                    try {
                        sc.nextLine();
                        System.out.println("Jsi u mazání filmů - zadej název filmu/animáku pro vymazání.");
                        name = sc.nextLine();
                        FilmManager.Delete(hraneFilmy, animovaneFilmy, name, toBeDeletedSQL_Live, toBeDeletedSQL_Animated);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                case 3 -> {
                    String name;
                    try {
                        sc.nextLine();
                        System.out.println("Jsi u editování filmů - zadej název filmu/animáku pro editnutí.");
                        name = sc.nextLine();
                        FilmManager.Edit(hraneFilmy, animovaneFilmy, name);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                case 4 -> FilmManager.VypisFilmu(hraneFilmy, animovaneFilmy);
                case 5 -> {
                    try {
                        sc.nextLine();
                        System.out.println("Zadej jméno filmu, kterému chceš přidat recenzi.");
                        String name = sc.nextLine();
                        ReviewManager.Pridani(hraneFilmy, animovaneFilmy, name);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
                case 6 -> {
                    String name;
                    sc.nextLine();
                    System.out.println("Zadej návev filmu, který chceš vypsat.");
                    name = sc.nextLine();
                    try{
                        FilmManager.Vyhledani(hraneFilmy, animovaneFilmy, name);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
                case 7 -> {
                    StaffManager.Herci(hraneFilmy);
                }
                case 8 -> {
                    StaffManager.Animatori(animovaneFilmy);
                }
                case 9 -> {
                    String name;
                    sc.nextLine();
                    System.out.println("Zadej herce/animátora:");
                    name = sc.nextLine();
                    FilmManager.VypisHerec(hraneFilmy, animovaneFilmy, name);
                }
                case 10 -> {
                    FileManager.Ulozeni(hraneFilmy, animovaneFilmy);
                }
                case 11 -> {
                    FileManager.Nacteni(hraneFilmy, animovaneFilmy);
                }
                case 12->{
                    UpdateSQL(hraneFilmy, animovaneFilmy, toBeDeletedSQL_Live, toBeDeletedSQL_Animated);
                    UlozeniDoSQL(hraneFilmy, animovaneFilmy);
                    run = false;
                }
            }
        } while (run);
    }
    public static void UlozeniDoSQL(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy){
        InsertQueries insertQueries = new InsertQueries();
        for (Film f : hraneFilmy){
            insertQueries.insertNewFilm(f.getName(), f.getDirector(), f.getRokVydani(), f.returnStaffName());
        }
        for (FilmAnimated f : animovaneFilmy){
            insertQueries.insertNewAnimatedFilm(f.getName(), f.getDirector(), f.getRokVydani(), f.getMinVek(), f.returnStaffName());
        }
        insertQueries.InsertReviewFilm(hraneFilmy);
        insertQueries.InsertReviewFilmAnimated(animovaneFilmy);
    }
    public static void NacteniZSQL(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy){
        SelectQueries selectQueries = new SelectQueries();
        selectQueries.LoadAnimatedFilmFromDB(animovaneFilmy);
        selectQueries.LoadFilmFromDB(hraneFilmy);
    }
    public static void UpdateSQL(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy, List<Integer> toBeDeletedSQL_Live, List<Integer> toBeDeletedSQL_Animated){
        UpdateQueries updateQueries = new UpdateQueries();
        DeleteQueries deleteQueries = new DeleteQueries();
        updateQueries.UpdateFilmDB(hraneFilmy);
        updateQueries.UpdateFilmAnimatedDB(animovaneFilmy);
        deleteQueries.DeleteFilm(hraneFilmy, animovaneFilmy, toBeDeletedSQL_Live, toBeDeletedSQL_Animated);
    }
}