package Film;

import Recenze.Recenze;
import java.util.ArrayList;
import java.util.List;

public class Film {
    private String name;
    private String director;
    private Integer rokVydani;
    private List<String> staff;
    private List<Recenze> recenze;

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
    public String getDirector()
    {
        return director;
    }
    public Integer getRokVydani(){ return rokVydani; }
    public void printAllStaff() {
        System.out.println("Herci:");
        staff.forEach((value) ->
        {
            System.out.println(String.format("%s", value));
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
