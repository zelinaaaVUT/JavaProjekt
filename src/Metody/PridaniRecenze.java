package Metody;

import Film.Film;
import Film.FilmAnimated;
import Recenze.RecenzeAnimated;
import Recenze.RecenzeLive;

import java.util.List;
import java.util.Scanner;

public class PridaniRecenze {
    Scanner sc = new Scanner(System.in);
    public void Pridani(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy, String name){
        String divak, komentar, hodnoceni;
        Integer found = 0;
        long counter;
        if (hraneFilmy.isEmpty() && animovaneFilmy.isEmpty()){
            System.out.println("Databáze s filmy/animáky je prázdná.");
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
                            f.addRecenze(new RecenzeAnimated(divak, komentar, hodnoceni));
                        }
                    }else {
                        System.out.println("Nenašel jsem tvůj hraný film v databázi.");
                        break;
                    }
                }
            }
        }
    }
}