package Metody;

import Film.Film;
import Film.FilmAnimated;

import java.util.List;

public class VypisHerecFilmy {

    public void Vypis(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy, String name){
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
