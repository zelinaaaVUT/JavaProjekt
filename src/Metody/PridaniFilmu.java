package Metody;

import Film.Film;
import Film.FilmAnimated;
import java.util.Scanner;

import java.util.List;

public class PridaniFilmu {
    public PridaniFilmu(){

    }
    public void Pridani(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy){
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
}
