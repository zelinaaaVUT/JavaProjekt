import Film.Film;
import Film.FilmAnimated;
import Handlers.FileHandler;
import Handlers.FilmHandler;
import Handlers.ReviewHandler;
import Handlers.StaffHandler;

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
                        FilmHandler.Pridani(hraneFilmy, animovaneFilmy);
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
                        FilmHandler.Delete(hraneFilmy, animovaneFilmy, name);
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
                        FilmHandler.Edit(hraneFilmy, animovaneFilmy, name);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                //vypis filmů
                case 4 -> FilmHandler.VypisFilmu(hraneFilmy, animovaneFilmy);
                //pridani recenze
                case 5 -> {
                    sc.nextLine();
                    try {
                        System.out.println("Zadej jméno filmu, kterému chceš přidat recenzi.");
                        String name = sc.nextLine();
                        ReviewHandler.Pridani(hraneFilmy, animovaneFilmy, name);
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
                        FilmHandler.Vyhledani(hraneFilmy, animovaneFilmy, name);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
                case 7 -> { //vypis herců ve více filmech
                    StaffHandler.Herci(hraneFilmy);
                }
                case 8 -> { //vypis animátorů ve více filmech
                    StaffHandler.Animatori(animovaneFilmy);
                }
                case 9 -> { //vypis filmů na kterých se herec podílel
                    String name;
                    sc.nextLine();
                    System.out.println("Zadej herce/animátora:");
                    name = sc.nextLine();
                    FilmHandler.VypisHerec(hraneFilmy, animovaneFilmy, name);
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
                    FileHandler.Ulozeni(hraneFilmy, animovaneFilmy);
                }
                //nacteni ze souboru
                case 12 -> {
                    FileHandler.Nacteni(hraneFilmy, animovaneFilmy);
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