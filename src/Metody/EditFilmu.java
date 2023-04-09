package Metody;

import Film.Film;
import Film.FilmAnimated;

import java.util.List;
import java.util.Scanner;

public class EditFilmu {
    Scanner sc = new Scanner(System.in);

    public void Edit(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy, String name) {
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
}