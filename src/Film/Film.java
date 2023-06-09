package Film;

import Recenze.Recenze;
import java.util.ArrayList;
import java.util.List;

public class Film {
    private String name;
    private String director;
    private Integer rokVydani;
    private int SQLID = 0;
    private boolean isChanged = false;
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

    public void addRecenze(Recenze novaRecenze) {
        recenze.add(novaRecenze);
    }

    public List<Recenze> getRecenze() {
        return recenze;
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

    public Integer getSQLID() {
        return SQLID;
    }

    public void setSQLID(int filmID){
        this.SQLID = filmID;
    }
    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged() {
        isChanged = true;
    }
}
