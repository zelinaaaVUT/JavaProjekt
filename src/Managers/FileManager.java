package Managers;

import Film.Film;
import Film.FilmAnimated;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class FileManager {
    public static void Ulozeni(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy){
        String aktDir = System.getProperty("user.dir");
        String dirHrane = aktDir + File.separator + "src" + File.separator + "MovieFiles/Hrane";
        String dirAnimovane = aktDir + File.separator + "src" + File.separator + "MovieFiles/Animovane";

        String str;

        for (Film f : hraneFilmy){
            str = f.getName() + ".txt";
            try {
                File file = new File(dirHrane, str);
                if (file.exists() && !f.isChanged()){
                    System.out.println("Film: " + f.getName() +  " v souborech už mám.");
                }else if (file.exists() && f.isChanged()){
                    file.delete();
                    FileWriter fw = new FileWriter(new File(dirHrane, str), true);
                    fw.write(f.getName()+";");
                    fw.write(f.getDirector()+";");
                    fw.write(f.getRokVydani()+";");
                    fw.write(f.getStaff()+";");
                    fw.close();
                }else {
                    file.delete();
                    FileWriter fw = new FileWriter(new File(dirHrane, str), true);
                    fw.write(f.getName()+";");
                    fw.write(f.getDirector()+";");
                    fw.write(f.getRokVydani()+";");
                    fw.write(f.getStaff()+";");
                    fw.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (FilmAnimated f : animovaneFilmy){
            str = f.getName() + ".txt";
            try {
                File file = new File(dirAnimovane, str);
                if (file.exists() && !f.isChanged()){
                    System.out.println("Animák: " + f.getName() +  " v souborech už mám.");
                }else if (file.exists() && f.isChanged()){
                    file.delete();
                    FileWriter fw = new FileWriter(new File(dirAnimovane, str), true);
                    fw.write(f.getName()+";");
                    fw.write(f.getDirector()+";");
                    fw.write(f.getRokVydani()+";");
                    fw.write(f.getMinVek()+";");
                    fw.write(f.getStaff()+";");
                    fw.close();
                }else {
                    FileWriter fw = new FileWriter(new File(dirAnimovane, str), true);
                    fw.write(f.getName()+";");
                    fw.write(f.getDirector()+";");
                    fw.write(f.getRokVydani()+";");
                    fw.write(f.getMinVek()+";");
                    fw.write(f.getStaff()+";");
                    fw.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void Nacteni(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy){
        String aktDir = System.getProperty("user.dir");
        String dirHrane = aktDir + File.separator + "src" + File.separator + "MovieFiles/Hrane";
        String dirAnimovane = aktDir + File.separator + "src" + File.separator + "MovieFiles/Animovane";

        List<String> hraneFilmySoubory = new ArrayList<>();
        List<String> animovaneFilmySoubory = new ArrayList<>();

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
                String name, director;
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
                    String[] staffSplit = (splited[3].substring(1, splited[3].length() - 1)).split(", ");
                    for (String s : staffSplit){
                        staffToBeAdded.add(s);
                    }
                    AddFilm(hraneFilmy, name, director, rokVydani, staffToBeAdded);
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
                String name, director;
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
                    String[] staffSplit = (splited[4].substring(1, splited[4].length() - 1)).split(", ");
                    for (String s : staffSplit){
                        staffToBeAdded.add(s);
                    }
                    AddAnimated(animovaneFilmy, name, director, rokVydani, doporucenyVek, staffToBeAdded);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            catch (IOException e){
                System.out.println(e);
            }
        });
    }

    public static void AddAnimated(List<FilmAnimated> animovaneFilmy, String name, String director, Integer rokVydani, Integer doporucenyVek, List<String> staffToBeAdded){
        ArrayList<FilmAnimated> tobeAdded = new ArrayList<>();
        boolean flag = false;
        for (FilmAnimated f : animovaneFilmy){
            if (f.getName().equals(name)){
                System.out.println("Animák s jménem: " + name + " už mám v programu načtený.");
                flag = true;
            } else {
                FilmAnimated film = new FilmAnimated(name, director, rokVydani, doporucenyVek);
                tobeAdded.add(film);
                for (String actor : staffToBeAdded){
                    film.addStaff(actor);
                }
            }
        }
        if (!flag){
            animovaneFilmy.add(tobeAdded.get(0));
        }
    }
    public static void AddFilm(List<Film> hraneFilmy, String name, String director, Integer rokVydani, List<String> staffToBeAdded){
        ArrayList<Film> tobeAdded = new ArrayList<>();
        boolean flag = false;
        for (Film f : hraneFilmy){
            if (f.getName().equals(name)){
                System.out.println("Film s jménem: " + name + " už mám v programu načtený.");
                flag = true;
            } else {
                Film film = new Film(name, director, rokVydani);
                tobeAdded.add(film);
                for (String actor : staffToBeAdded){
                    film.addStaff(actor);
                }
            }
        }
        if (!flag){
            hraneFilmy.add(tobeAdded.get(0));
        }
    }
}
