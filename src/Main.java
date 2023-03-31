import Film.Film;
import Film.FilmAnimated;

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
                case 2 -> {
                    String name;
                    sc.nextLine();
                    try {
                        System.out.println("Jsi u mazání filmů - zadej název filmu/animáku pro vymazání.");
                        name = sc.nextLine();

                        if (hraneFilmy.isEmpty() && animovaneFilmy.isEmpty()) {
                            System.out.println("Databaze s hranýma a animovanýma filmy je prázdná.");
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
                        System.out.println("hh");
                    }
                }
                case 3 -> {
                    if (hraneFilmy.isEmpty()) {
                        System.out.println("helllo");
                    }
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