import Film.Film;
import Film.FilmAnimated;
import Metody.*;
import Recenze.RecenzeAnimated;
import Recenze.RecenzeLive;
//import Metody.UlozeniDoSouboru;

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
                        PridaniFilmu pridaniFilmu = new PridaniFilmu();
                        pridaniFilmu.Pridani(hraneFilmy, animovaneFilmy);
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
                        DeleteFilmu deleteFilmu = new DeleteFilmu();
                        deleteFilmu.Delete(hraneFilmy, animovaneFilmy, name);
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
                        EditFilmu editFilmu = new EditFilmu();
                        editFilmu.Edit(hraneFilmy, animovaneFilmy, name);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                //vypis filmů
                case 4 -> {
                    VypisFilmu vypisFilmu = new VypisFilmu();
                    vypisFilmu.Vypis(hraneFilmy, animovaneFilmy);
                }
                //pridani recenze
                case 5 -> {
                    try {
                        sc.nextLine();
                        System.out.println("Zadej jméno filmu, kterému chceš přidat recenzi.");
                        String name = sc.nextLine();
                        PridaniRecenze pridaniRecenze = new PridaniRecenze();
                        pridaniRecenze.Pridani(hraneFilmy, animovaneFilmy, name);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
                case 6 -> { //vyhledani filmu not working
                    String name;
                    sc.nextLine();
                    System.out.println("Zadej návev filmu, který chceš vypsat.");
                    name = sc.nextLine();
                    for (Film f : hraneFilmy) {
                        if (f.getName().equals(name)) {
                            System.out.println("Takový film mám v databázi.");
                            System.out.println("Hraný film: " + f.getName());
                            System.out.println("Director: " + f.getDirector());
                            System.out.println("Rok vydaní: " + f.getRokVydani());
                            f.printAllStaff();
                            f.sortHrane();
                            f.printAllRecenze();
                            break;
                        } else {
                            System.out.println("Takový film v databázi nemám.");
                            break;
                        }
                    }
                }
                case 7 -> { //vypis herců ve více filmech
                    HerciMoreThanTwo herciMoreThanTwo = new HerciMoreThanTwo();
                    herciMoreThanTwo.Herci(hraneFilmy);
                }
                case 8 -> { //vypis animátorů ve více filmech
                    AnimatoriMoreThanTwo animatoriMoreThanTwo = new AnimatoriMoreThanTwo();
                    animatoriMoreThanTwo.Animatori(animovaneFilmy);
                }
                case 9 -> { //vypis filmů na kterých se herec podílel
                    String name;
                    sc.nextLine();
                    System.out.println("Zadej herce/animátora:");
                    name = sc.nextLine();
                    VypisHerecFilmy vypisHerecFilmy = new VypisHerecFilmy();
                    vypisHerecFilmy.Vypis(hraneFilmy, animovaneFilmy, name);
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
                    UlozeniDoSouboru uds = new UlozeniDoSouboru();
                    uds.Ulozeni(hraneFilmy, animovaneFilmy);
                }
                //nacteni ze souboru
                case 12 -> {
                    NacteniZeSouboru nacteniZeSouboru = new NacteniZeSouboru();
                    nacteniZeSouboru.Nacteni(hraneFilmy, animovaneFilmy);
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
        /*Film.Film film = new Film.Film("Nejaky film", "Kdo vi kdo", 2010);
        film.addRecenze(new RecenzeAnimated("Pavel Polak", "Jo dobry", 5, 13));
        film.addStaff("Petr");
        film.addStaff("Lucie");
        film.addStaff("Tomas");
        System.out.println("-------------");
        Film.Film film2 = new Film.Film("Matrix 2022", "No name", 2022);
        film2.addRecenze(new RecenzeLive("Petr Zelinka", "Fakt hnus", "***"));
        film.addStaff("No name... prostě");
        filmy.add(film);
        filmy.add(film2);
        for (Film.Film f : filmy)
        {
            System.out.println("Film.Film: " + f.getName());
            System.out.println("Director: " + f.getDirector());
            film.printAllStaff();
            f.printAllRecenze();
        }
        // Vypis filmu
        for (Film.Film f : filmy)
        {
            System.out.println("Film.Film: " + f.getName());
            System.out.println("Director: " + f.getDirector());
            film.printAllStaff();
            f.printAllRecenze();
        }
        // Vypis filmu
        String filterName = "Nejaky film";
        for (Film.Film f : filmy)
        {
            if (f.getName().equals(filterName))
            {
                System.out.println("Film.Film: " + f.getName());
                System.out.println("Director: " + f.getDirector());
                film.printAllStaff();
                f.printAllRecenze();
            }
        }
        // Úprava filmu:
        filterName = "Nejaky film";
        for (Film.Film f : filmy)
        {
            if (f.getName().equals(filterName))
            {
                // DO STUFF
            }
        }
        // Smazání filmu
        filterName = "Nejaky film";
        for (Film.Film f : filmy)
        {
            if (f.getName().equals(filterName))
            {
                filmy.remove(f);
                break;
            }
        }*/
    }
}