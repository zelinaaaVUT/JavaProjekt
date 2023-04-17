package Metody;
import Film.Film;
import Film.FilmAnimated;
import java.util.List;
import java.util.Scanner;
public class VypisFilmu {
    public void Vypis(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy){
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
}
