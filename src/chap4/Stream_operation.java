package chap4;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tzy on 2017/7/21.
 */
public class Stream_operation {
    public static void main(String[] args) {
        List<String> names=Dish.menu.stream()
                .filter((Dish d)->d.getCalories()>300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(names);

        List<String> names1=Dish.menu.stream()
                .filter((Dish d)->{
                    System.out.println("filter "+d.getName());
                    return d.getCalories()>300;})
                .map((Dish d)->{
                    System.out.println("map "+d.getName());
                    return d.getName();})
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(names1);
    }

}
