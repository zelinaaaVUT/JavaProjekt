package Metody;

import Film.FilmAnimated;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AnimatoriMoreThanTwo {
    public void Animatori(List<FilmAnimated> animovaneFilmy){
        Integer count = 0;
        String filmy = "";
        Map<String, Integer> test = new HashMap<>();
        Map<String, String> test2 = new HashMap<>();

        //získání počtu v kolikati filmech se herec vyskytuje
        for (FilmAnimated f : animovaneFilmy){
            List<String> staff = f.returnStaffName();
            for (String herec : staff){
                count = test.getOrDefault(herec, 0);
                test.put(herec, count + 1);
            }
        }
        Iterator<Map.Entry<String, Integer>> iterator = test.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() < 2){
                iterator.remove();
            }
        }
        for (FilmAnimated f : animovaneFilmy){
            List<String> staff = f.returnStaffName();
            for (String herec : staff){
                filmy = test2.getOrDefault(herec, "");
                if (test.containsKey(herec)){
                    test2.put(herec, filmy + f.getName()+ ", ");
                }
            }
        }
        if (test2.isEmpty()){
            System.out.println("Žádný animátor se nepodível na více než jednom filmu.");
        }else {
            test2.forEach((key, value)->{
                System.out.println(key + " {" + value + "}");
            });
        }
    }
}
