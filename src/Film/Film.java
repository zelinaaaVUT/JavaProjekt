package Film;

import Recenze.Recenze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Film {
    private String name;
    private String director;
    private Integer rokVydani;
    private final List<String> staff;
    private final List<Recenze> recenze;

    public Film(String name, String director, Integer rokVydani) {
        this.name = name;
        this.director = director;
        this.rokVydani = rokVydani;
        staff = new ArrayList<>();
        recenze = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getRokVydani() {
        return rokVydani;
    }

    public void setRokVydani(Integer rokVydani) {
        this.rokVydani = rokVydani;
    }

    public void printAllStaff() {
        System.out.println("Herci:");
        staff.forEach((value) ->
        {
            System.out.printf("%s%n", value);
        });
    }

    public void sortHrane(){

    }

    public void addRecenze(Recenze novaRecenze) {
        recenze.add(novaRecenze);
    }

    public void printAllRecenze() {
        recenze.forEach((value) ->
        {
            value.printRecenze();
        });
    }

    public List<String> getStaff() {
        return staff;
    }

    public List<String> returnStaffName(){
        return staff;
    }

    public void addStaff(String name) {
        staff.add(name);
    }

    public void removeStaff(String name) {
        staff.remove(name);
    }
}
