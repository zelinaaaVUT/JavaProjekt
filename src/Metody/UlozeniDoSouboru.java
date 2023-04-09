package Metody;

import Film.Film;
import Film.FilmAnimated;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UlozeniDoSouboru {
    public UlozeniDoSouboru(){
    }
    public void Ulozeni(List<Film> hraneFilmy, List<FilmAnimated> animovaneFilmy){
        String aktDir = System.getProperty("user.dir");
        String dir = aktDir + File.separator + "src" + File.separator + "MovieFiles";

        //String dir = "/home/zelinaaa/Documents/VUT/PC2T/JavaProjektGood/src/MovieFiles";
        String str;

        for (Film f : hraneFilmy){
            str = f.getName() + ".txt";
            try {
                FileWriter fw = new FileWriter(new File(dir, str), true);
                fw.write(f.getName()+";");
                fw.write(f.getDirector()+";");
                fw.write(f.getRokVydani()+";");
                fw.write(f.getStaff()+";");
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
