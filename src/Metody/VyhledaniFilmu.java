package Metody;

import Film.Film;
import Film.FilmAnimated;
import Recenze.Recenze;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VyhledaniFilmu {

    private static int countHowManyStars(Recenze recenzeLive) {
        int counter = 0;

        for (int i = 0; i < recenzeLive.getHodnoceni().length(); i++) {
            if (recenzeLive.getHodnoceni().charAt(i) == '*') {
                counter++;
            }
        }
        return counter;
    }

    public void vyhledani(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy, String name) {
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
}
