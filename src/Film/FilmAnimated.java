package Film;

import java.util.ArrayList;
import java.util.List;

public class FilmAnimated extends Film{
    private Integer minVek;
    private List<String> staff;
    public FilmAnimated(String name, String director, Integer rokVydani, Integer minVek) {
        super(name, director, rokVydani);
        this.minVek = minVek;
        staff = new ArrayList<>();
    }

    public Integer getMinVek(){
        return minVek;
    }
    public void addStaff(String name) {
        staff.add(name);
    }
    @Override
    public void printAllStaff() {
        System.out.println("Animátoří:");
        staff.forEach((value) ->
        {
            System.out.println(String.format("%s", value));
        });
    }
}
