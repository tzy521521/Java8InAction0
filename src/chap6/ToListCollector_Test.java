package chap6;

import chap4.Dish;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tzy on 2017/7/23.
 */
public class ToListCollector_Test {
    public static void main(String[] args) {
        List<Dish> dishes=Dish.menu.stream().collect(Collectors.toList());
        System.out.println(dishes);

        List<Dish> dishes1=Dish.menu.stream().collect(new ToListCollector<>());
        System.out.println(dishes1);
    }
}
