package Film;

import java.util.ArrayList;
import java.util.List;

public class FilmAnimated extends Film {
    private Integer minVek;
    private final List<String> staff;

    public FilmAnimated(String name, String director, Integer rokVydani, Integer minVek) {
        super(name, director, rokVydani);
        this.minVek = minVek;
        staff = new ArrayList<>();
    }

    public Integer getMinVek() {
        return minVek;
    }

    public void setMinVek(Integer minVek) {
        this.minVek = minVek;
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

    @Override
    public void printAllStaff() {
        System.out.println("Animátoří:");
        staff.forEach((value) ->
        {
            System.out.printf("%s%n", value);
        });
    }
}
