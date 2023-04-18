package Handlers;

import Film.Film;
import Film.FilmAnimated;
import Recenze.Recenze;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public final class FilmHandler {
    public static void Pridani(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy){
        Scanner sc = new Scanner(System.in);
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
    }
    public static void Delete(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy, String name){
        if (hraneFilmy.isEmpty() && animovaneFilmy.isEmpty()) {
            System.out.println("Databaze s hranými a animovanými filmy je prázdná.");
        }else{
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
        }
    }
    public static void Edit(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy, String name) {
        Scanner sc = new Scanner(System.in);
        String director, actor = "", vyberStaff;
        Integer rokVydani, vyber, minVek;
        boolean zamestanci = true;
        if (hraneFilmy.isEmpty() && animovaneFilmy.isEmpty()) {
            System.out.println("Databaze s hranými a animovanými filmy je prázdná.");
        } else {
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
                                switch (vyberStaff) {
                                    case "s" -> {
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
                                switch (vyberStaff) {
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
                } else {
                    System.out.println("takový film nemám v databázi.");
                    break;
                }
            }
        }
    }
    public static void VypisFilmu(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy){
        if (hraneFilmy.isEmpty() && animovaneFilmy.isEmpty()){
            System.out.println("Databáze je prázdná.");
            System.out.println("-----------------------------------------.");
        }else {
            if (hraneFilmy.isEmpty()) {
                System.out.println("Databáze s hranými filmy je prázdná.");
                System.out.println("-----------------------------------------.");
            }else {
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
                    System.out.println("Animovany film: " + f.getName());
                    System.out.println("Director: " + f.getDirector());
                    System.out.println("Rok vydaní: " + f.getRokVydani());
                    System.out.println("Doporučený věk: " + f.getMinVek());
                    f.printAllStaff();
                    System.out.println("-----------------------------------------.");
                }
            }
        }
    }

    private static int countHowManyStars(Recenze recenzeLive) {
        int counter = 0;

        for (int i = 0; i < recenzeLive.getHodnoceni().length(); i++) {
            if (recenzeLive.getHodnoceni().charAt(i) == '*') {
                counter++;
            }
        }
        return counter;
    }
    public static void Vyhledani(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy, String name) {
        for (Film f : hraneFilmy) {
            if (f.getName().equals(name)) {
                List<Recenze> recenze = f.getRecenze();

                Collections.sort(recenze, new Comparator<Recenze>() {
                    public int compare(Recenze o1, Recenze o2) {
                        return Integer.compare(countHowManyStars(o2), countHowManyStars(o1));
                    }
                });
                System.out.println("-----------------------------------------.");
                System.out.println("Film: " + f.getName());
                System.out.println("Director: " + f.getDirector());
                System.out.println("Rok vydaní: " + f.getRokVydani());
                System.out.println("Hodnocení sestupně: ");
                for (Recenze serazene : recenze){
                    System.out.printf("Jmeno divaka: %s, Komentar: %s, Hodnoceni: %s\n",
                            serazene.getJmenoDivaka(), serazene.getKomentar(), serazene.getHodnoceni());
                }
                f.printAllStaff();
                System.out.println("-----------------------------------------.");
            }
        }
        for (FilmAnimated f : animovaneFilmy) {
            if (f.getName().equals(name)) {
                List<Recenze> recenze = f.getRecenze();

                Collections.sort(recenze, new Comparator<Recenze>() {
                    public int compare(Recenze o1, Recenze o2) {
                        return Double.compare(Double.parseDouble(o2.getHodnoceni()), Double.parseDouble(o1.getHodnoceni()));
                    }
                });
                System.out.println("-----------------------------------------.");
                System.out.println("Film: " + f.getName());
                System.out.println("Director: " + f.getDirector());
                System.out.println("Rok vydaní: " + f.getRokVydani());
                System.out.println("Doporučený věk: " + f.getMinVek());
                System.out.println("Hodnocení sestupně: ");
                for (Recenze serazene : recenze){
                    System.out.printf("Jmeno divaka: %s, Komentar: %s, Hodnoceni: %s\n",
                            serazene.getJmenoDivaka(), serazene.getKomentar(), serazene.getHodnoceni());
                }
                f.printAllStaff();
                System.out.println("-----------------------------------------.");
            }
        }
    }
    public static void VypisHerec(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy, String name){
        for (Film f : hraneFilmy){
            if (f.getStaff().contains(name)){
                System.out.println("Hraný film: " +f.getName());
            }
        }
        for (FilmAnimated f : animovaneFilmy){
            if (f.getStaff().contains(name)){
                System.out.println("Animák: " + f.getName());
            }
        }
    }
}
