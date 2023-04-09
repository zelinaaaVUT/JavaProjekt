package Metody;

import Film.Film;
import Film.FilmAnimated;
import java.util.Scanner;

import java.util.List;

public class DeleteFilmu {
    Scanner sc = new Scanner(System.in);
    public void Delete(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy, String name){
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
}
