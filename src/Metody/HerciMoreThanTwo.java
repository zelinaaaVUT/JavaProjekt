package Metody;
import Film.Film;

import java.util.*;

public class HerciMoreThanTwo {

    public void Herci(List<Film> hraneFilmy){
        Integer count = 0;
        String filmy = "";
        Map<String, Integer> test = new HashMap<>();
        Map<String, String> test2 = new HashMap<>();

        //získání počtu v kolikati filmech se herec vyskytuje
        for (Film f : hraneFilmy){
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
        for (Film f : hraneFilmy){
            List<String> staff = f.returnStaffName();
            for (String herec : staff){
                filmy = test2.getOrDefault(herec, "");
                if (test.containsKey(herec)){
                    test2.put(herec, filmy + f.getName()+ ", ");
                }
            }
        }
        if (test2.isEmpty()){
            System.out.println("Žádný herec se nepodível na více než jednom filmu.");
        }else {
            test2.forEach((key, value)->{
                System.out.println(key + " {" + value + "}");
            });
        }
    }
}
