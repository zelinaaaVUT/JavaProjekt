import Film.Film;
import Film.FilmAnimated;
import Recenze.RecenzeAnimated;
import Recenze.RecenzeLive;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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
                        Integer vyber, rokVydani, doporucenyVek;
                        String name, director, actor;
                        boolean zamestanci = true;
                        System.out.println("Vítej u přidání filmu. Vyber 1 - pro přidání hraného filmu" +
                                "2 - pro přidání animáku.");
                        vyber = sc.nextInt();
                        if (vyber == 1) {
                            System.out.println("Vybral jsi hraný film.");
                            sc.nextLine();
                            System.out.println("Zadej název filmu.");
                            name = sc.nextLine();
                            System.out.println("Zadej jméno režíséra.");
                            director = sc.nextLine();
                            System.out.println("Zadej rok vydání.");
                            rokVydani = sc.nextInt();
                            Film film = new Film(name, director, rokVydani);

                            System.out.println("Ted budeš zadávat herce - ukonci přidávání napsáním s stop.");
                            while (zamestanci) {
                                //sc.nextLine();
                                actor = sc.nextLine();
                                if (actor.equals("s")) {
                                    zamestanci = false;
                                } else if (actor.isEmpty()) {
                                } else {
                                    film.addStaff(actor);
                                }
                            }
                            hraneFilmy.add(film);
                        } else if (vyber == 2) {
                            System.out.println("Vybral jsi animovaný film.");
                            sc.nextLine();
                            System.out.println("Zadej název filmu.");
                            name = sc.nextLine();
                            System.out.println("Zadej jméno režíséra.");
                            director = sc.nextLine();
                            System.out.println("Zadej rok vydání.");
                            rokVydani = sc.nextInt();
                            System.out.println("Zadej doporučený věk.");
                            doporucenyVek = sc.nextInt();
                            FilmAnimated film = new FilmAnimated(name, director, rokVydani, doporucenyVek);

                            System.out.println("Ted budeš zadávat animátory - ukonci přidávání napsáním s stop.");
                            while (zamestanci) {
                                actor = sc.nextLine();
                                if (actor.equals("s")) {
                                    zamestanci = false;
                                } else if (actor.isEmpty()) {
                                } else {
                                    film.addStaff(actor);
                                }
                            }
                            animovaneFilmy.add(film);
                        }
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

                        if (hraneFilmy.isEmpty() && animovaneFilmy.isEmpty()) {
                            System.out.println("Databaze s hranými a animovanými filmy je prázdná.");
                            break;
                        }
                        for (Film f : hraneFilmy) {
                            if (f.getName().equals(name)) {
                                hraneFilmy.remove(f);
                                System.out.printf("Odebral jsem hraný film s názvem: %s%n", name);
                                break;
                            } else {
                                System.out.println("Takový film v databázi nemám.");
                                break;
                            }
                        }
                        for (FilmAnimated f : animovaneFilmy) {
                            if (f.getName().equals(name)) {
                                animovaneFilmy.remove(f);
                                System.out.printf("Odebral jsi animovaný film s názvem: %s%n", name);
                                break;
                            } else {
                                System.out.println("Takový animák v databázi nemám.");
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                //edit
                case 3 -> {
                    String name, director, actor = "", vyberStaff;
                    Integer rokVydani, vyber, minVek;
                    boolean zamestanci = true;
                    sc.nextLine();
                    try {
                        System.out.println("Jsi u editování filmů - zadej název filmu/animáku pro editnutí.");
                        name = sc.nextLine();
                        if (hraneFilmy.isEmpty() && animovaneFilmy.isEmpty()) {
                            System.out.println("Databaze s hranými a animovanými filmy je prázdná.");
                            break;
                        }
                        for (Film f : hraneFilmy) {
                            if (f.getName().equals(name)) {
                                name = f.getName();
                                director = f.getDirector();
                                rokVydani = f.getRokVydani();

                                System.out.println("Film jsem našel v databázi, co chceš upravit?");
                                System.out.println("1 - název filmu, 2 - režiséra filmu, 3 - rok vydání, 4 - herce");
                                vyber = sc.nextInt();
                                switch (vyber) {
                                    case 1 -> {
                                        sc.nextLine();
                                        System.out.printf("Jsi u změny jména. Staré jméno: -> %s <-. Zadej nové: ", name);
                                        name = sc.nextLine();
                                        f.setName(name);
                                    }
                                    case 2 -> {
                                        sc.nextLine();
                                        System.out.printf("Jsi u změny režiséra. Starý režisér: -> %s <-. Zadej nové: ", director);
                                        director = sc.nextLine();
                                        f.setDirector(director);
                                    }
                                    case 3 -> {
                                        sc.nextLine();
                                        System.out.printf("Jsi u změny roku vydání filmu. Starý rok vydání: -> %d <-. Zadej nové: ", rokVydani);
                                        rokVydani = sc.nextInt();
                                        f.setRokVydani(rokVydani);
                                    }
                                    case 4 -> {
                                        sc.nextLine();
                                        System.out.print("Jsi u změny herců. Staří ");
                                        f.printAllStaff();
                                        System.out.println("Zadej r pro odebrání herce, a pro přidání herce, s pro stop");
                                        while (zamestanci) {
                                            System.out.print("Zadej akci: ");
                                            vyberStaff = sc.nextLine();
                                            switch (vyberStaff){
                                                case "s" ->{
                                                    System.out.println("Ukončil jsi edit herců.");
                                                    zamestanci = false;
                                                }
                                                case "r" -> {
                                                    System.out.println("Jsi u odebrání herce, zadej jméno toho koho chceš odebrat a pak s - pro stop odebírání.");
                                                    while (true) {
                                                        actor = sc.nextLine();
                                                        if (actor.equals("s")) {
                                                            break;
                                                        } else {
                                                            try {
                                                                f.removeStaff(actor);
                                                            } catch (Exception e) {
                                                                System.out.println("Nelze odebrat herce.");
                                                            }
                                                        }
                                                    }
                                                }
                                                case "a" -> {
                                                    System.out.println("Jsi u přídání herců, zadej jméno toho koho chceš přídat a pak s - pro stop přidávání.");
                                                    while (true) {
                                                        actor = sc.nextLine();
                                                        if (actor.equals("s")) {
                                                            break;
                                                        } else {
                                                            try {
                                                                f.addStaff(actor);
                                                            } catch (Exception e) {
                                                                System.out.println("Nelze přidat herce.");
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                System.out.println("takový film nemám v databázi.");
                                break;
                            }
                        }
                        for (FilmAnimated f : animovaneFilmy) {
                            if (f.getName().equals(name)) {
                                name = f.getName();
                                director = f.getDirector();
                                rokVydani = f.getRokVydani();
                                minVek = f.getMinVek();

                                System.out.println("Film jsem našel v databázi, co chceš upravit?");
                                System.out.println("1 - název filmu, 2 - režiséra filmu, 3 - rok vydání, 4 - doporučený věk, 5 - herce");
                                vyber = sc.nextInt();
                                switch (vyber) {
                                    case 1 -> {
                                        sc.nextLine();
                                        System.out.printf("Jsi u změny jména. Staré jméno: -> %s <-. Zadej nové: ", name);
                                        name = sc.nextLine();
                                        f.setName(name);
                                    }
                                    case 2 -> {
                                        sc.nextLine();
                                        System.out.printf("Jsi u změny režiséra. Starý režisér: -> %s <-. Zadej nové: ", director);
                                        director = sc.nextLine();
                                        f.setDirector(director);
                                    }
                                    case 3 -> {
                                        sc.nextLine();
                                        System.out.printf("Jsi u změny roku vydání filmu. Starý rok vydání: -> %d <-. Zadej nové: ", rokVydani);
                                        rokVydani = sc.nextInt();
                                        f.setRokVydani(rokVydani);
                                    }
                                    case 4 -> {
                                        sc.nextLine();
                                        System.out.printf("Jsi u změny doporučeného věku filmu. Starý rok doporučení: -> %d <-. Zadej nové: ", minVek);
                                        minVek = sc.nextInt();
                                        f.setMinVek(minVek);
                                    }
                                    case 5 -> {
                                        sc.nextLine();
                                        System.out.print("Jsi u změny animátorů. Staří ");
                                        f.printAllStaff();
                                        System.out.println("Zadej r pro odebrání animátora, a pro přidání animátora, s pro stop");
                                        while (zamestanci) {
                                            System.out.print("Zadej akci: ");
                                            vyberStaff = sc.nextLine();
                                            switch (vyberStaff){
                                                case "s" -> {
                                                    System.out.println("Ukončil jsi edit animátorů.");
                                                    zamestanci = false;
                                                }
                                                case "r" -> {
                                                    System.out.println("Jsi u odebrání animátora, zadej jméno toho koho chceš odebrat a pak s - pro stop odebírání.");
                                                    while (true) {
                                                        actor = sc.nextLine();
                                                        if (actor.equals("s")) {
                                                            break;
                                                        } else {
                                                            try {
                                                                f.removeStaff(actor);
                                                            } catch (Exception e) {
                                                                System.out.println("Nelze odebrat animátora.");
                                                            }
                                                        }
                                                    }
                                                }
                                                case "a" -> {
                                                    System.out.println("Jsi u přídání animátorů, zadej jméno toho koho chceš přídat a pak s - pro stop přidávání.");
                                                    while (true) {
                                                        actor = sc.nextLine();
                                                        if (actor.equals("s")) {
                                                            break;
                                                        } else {
                                                            try {
                                                                f.addStaff(actor);
                                                            } catch (Exception e) {
                                                                System.out.println("Nelze přidat animátora.");
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                                break;
                            } else {
                                System.out.println("Takový film v databázi nemám.");
                                break;
                            }
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                //vypis filmů
                case 4 -> {
                    if (hraneFilmy.isEmpty()) {
                        System.out.println("Databáze s hranými filmy je prázdná.");
                        System.out.println("-----------------------------------------.");
                    }
                    for (Film f : hraneFilmy) {
                        System.out.println("-----------------------------------------.");
                        System.out.println("Film: " + f.getName());
                        System.out.println("Director: " + f.getDirector());
                        System.out.println("Rok vydaní: " + f.getRokVydani());
                        //System.out.println("Doporučený věk: " + f.getMinVek());
                        f.printAllStaff();
                        System.out.println("-----------------------------------------.");
                    }
                    if (animovaneFilmy.isEmpty()) {
                        System.out.println("Databáze s animovanými filmy je prázdná.");
                        System.out.println("-----------------------------------------.");
                    }
                    for (FilmAnimated f : animovaneFilmy) {
                        System.out.println("-----------------------------------------.");
                        System.out.println("Film: " + f.getName());
                        System.out.println("Director: " + f.getDirector());
                        System.out.println("Rok vydaní: " + f.getRokVydani());
                        System.out.println("Doporučený věk: " + f.getMinVek());
                        f.printAllStaff();
                        System.out.println("-----------------------------------------.");
                    }
                }
                //pridani recenze
                case 5 -> {
                    String name, divak, komentar, hodnoceni;
                    Integer found = 0;
                    long counter;
                    sc.nextLine();
                    System.out.println("Zadej jméno filmu, kterému chceš přidat recenzi.");
                    name = sc.nextLine();
                    if (hraneFilmy.isEmpty() && animovaneFilmy.isEmpty()){
                        System.out.println("Databáze s filmy je prázdná.");
                    }else {
                        for (Film f : hraneFilmy){
                            if (f.getName().equals(name)){
                                found = 1;
                            }
                            if (found == 1){
                                System.out.println("Našel jsem tvůj hraný film v databázi. Nyní můžeš přidat recenzi.");
                                System.out.print("Zadej jméno recenzenta: ");
                                divak = sc.nextLine();
                                System.out.print("Zadej komentář(volitelne): ");
                                komentar = sc.nextLine();
                                System.out.print("Zadej hodnocení - * 1-5: ");
                                hodnoceni = sc.nextLine();
                                counter = hodnoceni.chars().filter(ch -> ch == '*').count();
                                if (counter < 1 || counter > 5){
                                    System.out.println("Špatně zadané hodnocení. Musíš zadat 1-5 *. Začni znovu.");
                                    break;
                                }else {
                                    System.out.println("Recenze přidána.");
                                    f.addRecenze(new RecenzeLive(divak, komentar, hodnoceni));
                                }
                            }else {
                                System.out.println("Nenašel jsem tvůj hraný film v databázi.");
                                break;
                            }
                        }
                        for (FilmAnimated f : animovaneFilmy){
                            if (f.getName().equals(name)){
                                found = 1;
                                if (found == 1){
                                    System.out.println("Našel jsem tvůj animovaný film v databázi. Nyní můžeš přidat recenzi.");
                                    System.out.print("Zadej jméno recenzenta: ");
                                    divak = sc.nextLine();
                                    System.out.print("Zadej komentář(volitelne): ");
                                    komentar = sc.nextLine();
                                    System.out.print("Zadej hodnocení - 1-10: ");
                                    hodnoceni = sc.nextLine();
                                    if (Integer.parseInt(hodnoceni) < 1 || Integer.parseInt(hodnoceni) > 10){
                                        System.out.println("Špatně zadané hodnocení. Musíš zadat 1-10. Začni znovu.");
                                        break;
                                    }else {
                                        System.out.println("Recenze přidána.");
                                        f.addRecenze(new RecenzeAnimated(divak, komentar, Integer.parseInt(hodnoceni)));
                                    }
                                }else {
                                    System.out.println("Nenašel jsem tvůj hraný film v databázi.");
                                    break;
                                }
                            }
                        }
                    }

                }
                case 6 -> { //vyhledani filmu

                }
                case 7 -> { //vypis herců ve více filmech

                }
                case 8 -> { //vypis filmů na kterých se herec podílel

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