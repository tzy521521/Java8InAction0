package chap3;

import chap2.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by tzy on 2017/7/21.
 */
public class Sorting {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80,"green"),
                new Apple(160, "green"),
                new Apple(155, "red")
        );

        /*
        Comparator<Apple> comparator=(Apple apple1, Apple apple2)->apple1.getWeight().compareTo(apple2.getWeight());
        inventory.sort(comparator);
        System.out.println(inventory);
         */

        inventory.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(inventory);

        List<String> str = Arrays.asList("a","b","A","B");
        str.sort(String::compareToIgnoreCase);
        System.out.println(str);

    }
}
