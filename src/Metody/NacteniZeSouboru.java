package Metody;
import Film.FilmAnimated;
import Film.Film;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NacteniZeSouboru {
    public void Nacteni(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy){
        String aktDir = System.getProperty("user.dir");
        String dirHrane = aktDir + File.separator + "src" + File.separator + "MovieFiles/Hrane";
        String dirAnimovane = aktDir + File.separator + "src" + File.separator + "MovieFiles/Animovane";

        String str;

        List<String> hraneFilmySoubory = new ArrayList<String>();
        List<String> animovaneFilmySoubory = new ArrayList<String>();

        File[] hraneFolder = new File(dirHrane).listFiles();
        File[] animovaneFolder = new File(dirAnimovane).listFiles();

        for (File file : hraneFolder){
            if (file.isFile()){
                hraneFilmySoubory.add(file.getName());
            }
        }
        for (File file : animovaneFolder){
            if (file.isFile()){
                animovaneFilmySoubory.add(file.getName());
            }
        }

        hraneFilmySoubory.forEach((value)->{
            File vstupniSoubor = new File(dirHrane + "/" + value);
            try {
                String name, director, staff;
                Integer rokVydani;
                List<String> staffToBeAdded = new ArrayList<>();
                FileReader fr = new FileReader(vstupniSoubor);
                BufferedReader in = new BufferedReader(fr);
                String radek;
                while((radek = in.readLine()) != null){
                    String[] splited = radek.split(";");
                    name = splited[0];
                    director = splited[1];
                    rokVydani = Integer.valueOf(splited[2]);
                    String[] staffSplit = (splited[3].substring(1, splited[3].length() - 1)).split(", "); //removes brackets, split by comma and put it in array
                    for (String s : staffSplit){
                        staffToBeAdded.add(s);
                    }
                    Film film = new Film(name, director, rokVydani);
                    hraneFilmy.add(film);
                    for (String actor : staffToBeAdded){
                        film.addStaff(actor);
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            catch (IOException e){
                System.out.println(e);
            }
        });

        animovaneFilmySoubory.forEach((value)->{
            File vstupniSoubor = new File(dirAnimovane + "/" + value);
            try {
                String name, director, staff;
                Integer rokVydani, doporucenyVek;
                List<String> staffToBeAdded = new ArrayList<>();
                FileReader fr = new FileReader(vstupniSoubor);
                BufferedReader in = new BufferedReader(fr);
                String radek;
                while((radek = in.readLine()) != null){
                    String[] splited = radek.split(";");
                    name = splited[0];
                    director = splited[1];
                    rokVydani = Integer.valueOf(splited[2]);
                    doporucenyVek = Integer.valueOf(splited[3]);
                    String[] staffSplit = (splited[4].substring(1, splited[4].length() - 1)).split(", "); //removes brackets, split by comma and put it in array
                    for (String s : staffSplit){
                        staffToBeAdded.add(s);
                    }
                    FilmAnimated film = new FilmAnimated(name, director, rokVydani, doporucenyVek);
                    animovaneFilmy.add(film);
                    for (String actor : staffToBeAdded){
                        film.addStaff(actor);
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            catch (IOException e){
                System.out.println(e);
            }
        });

    }
}
