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
import dbconn.DBConnection;
import crud.InsertQueries;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        boolean run = true;
        int input;
        List<Film> hraneFilmy = new ArrayList<>();
        List<FilmAnimated> animovaneFilmy = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Zadej input - čísla TBD");
            input = sc.nextInt();
            switch (input) {
                //add
                case 1 -> {
                    try {
                        FilmManager.Pridani(hraneFilmy, animovaneFilmy);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                //delete
                case 2 -> {
                    String name;
                    sc.nextLine();
                    try {
                        System.out.println("Jsi u mazání filmů - zadej název filmu/animáku pro vymazání.");
                        name = sc.nextLine();
                        FilmManager.Delete(hraneFilmy, animovaneFilmy, name);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                //edit
                case 3 -> {
                    String name;
                    sc.nextLine();
                    try {
                        System.out.println("Jsi u editování filmů - zadej název filmu/animáku pro editnutí.");
                        name = sc.nextLine();
                        FilmManager.Edit(hraneFilmy, animovaneFilmy, name);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                //vypis filmů
                case 4 -> FilmManager.VypisFilmu(hraneFilmy, animovaneFilmy);
                //pridani recenze
                case 5 -> {
                    sc.nextLine();
                    try {
                        System.out.println("Zadej jméno filmu, kterému chceš přidat recenzi.");
                        String name = sc.nextLine();
                        ReviewManager.Pridani(hraneFilmy, animovaneFilmy, name);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
                case 6 -> { //vyhledani filmu not working
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
                case 7 -> { //vypis herců ve více filmech
                    StaffManager.Herci(hraneFilmy);
                }
                case 8 -> { //vypis animátorů ve více filmech
                    StaffManager.Animatori(animovaneFilmy);
                }
                case 9 -> { //vypis filmů na kterých se herec podílel
                    String name;
                    sc.nextLine();
                    System.out.println("Zadej herce/animátora:");
                    name = sc.nextLine();
                    FilmManager.VypisHerec(hraneFilmy, animovaneFilmy, name);
                }

                case 10 -> {
                    if (hraneFilmy.isEmpty()) {
                        System.out.println("helllo");
                    }
                    for (Film f : hraneFilmy) {
                        System.out.println("Film: " + f.getName());
                        System.out.println("Director: " + f.getDirector());
                        System.out.println("Rok vydaní: " + f.getRokVydani());
                        //System.out.println("Doporučený věk: " + f.getMinVek());
                        f.printAllStaff();
                        f.printAllRecenze();
                    }
                }
                //ulozeni do souboru
                case 11 -> {
                    FileManager.Ulozeni(hraneFilmy, animovaneFilmy);
                }
                //nacteni ze souboru
                case 12 -> {
                    FileManager.Nacteni(hraneFilmy, animovaneFilmy);
                }
                case 13 ->{
                    InsertQueries insertQueries = new InsertQueries();
                    insertQueries.performInsertQuery("INSERT INTO FilmHrany " + "(nazev,reziser,rok_vydani)"
                            + "VALUES('Avatar', 'James Cameron','2009')");
                }
            }
        } while (run);

        for (FilmAnimated f : animovaneFilmy) {
            System.out.println("Film.Film: " + f.getName());
            System.out.println("Director: " + f.getDirector());
            System.out.println("Rok vydaní: " + f.getRokVydani());
            System.out.println("Doporučený věk: " + f.getMinVek());
            f.printAllStaff();
            f.printAllRecenze();
        }
    }
}