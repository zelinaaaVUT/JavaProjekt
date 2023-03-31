package Film;

import Recenze.Recenze;

import java.util.ArrayList;
import java.util.List;

public class Film {
    private final String name;
    private final String director;
    private final Integer rokVydani;
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

    public String getDirector() {
        return director;
    }

    public Integer getRokVydani() {
        return rokVydani;
    }

    public void printAllStaff() {
        System.out.println("Herci:");
        staff.forEach((value) ->
        {
            System.out.printf("%s%n", value);
        });
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

    public void addStaff(String name) {
        staff.add(name);
    }
}
