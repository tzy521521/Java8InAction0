package chap6;

import chap4.Dish;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tzy on 2017/7/23.
 * 6.4分区
 */
public class Partitioning {
    public static void main(String[] args) {
        System.out.println("Dishes partitioned by vegetarian: " + partitionByVegeterian().get(Boolean.TRUE));
        System.out.println("Dishes partitioned by vegetarian: " + partitionByVegeterian1());
        System.out.println("Vegetarian Dishes by type: " + vegetarianDishesByType());
        System.out.println("Most caloric dishes by vegetarian: " + mostCaloricPartitionedByVegetarian());


        System.out.println("M " + multistagePartitioned());
        System.out.println(multistagePartitioned1());

    }
    //分区是分组的特殊情况:油一个谓词作为分类函数。
    private static Map<Boolean, List<Dish>> partitionByVegeterian() {
        return Dish.menu.stream().collect(Collectors.partitioningBy(
                Dish::isVegetarian
        ));
    }
    //
    private static List<Dish> partitionByVegeterian1() {
        return Dish.menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
    }

    //Collectors.partitioningBy()有个重载的版本。
    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType(){
        return Dish.menu.stream().collect(Collectors.partitioningBy(
                Dish::isVegetarian,
                Collectors.groupingBy(Dish::getType)
        ));
    }
    //
    private static Map<Boolean, Dish> mostCaloricPartitionedByVegetarian(){
        return Dish.menu.stream().collect(Collectors.partitioningBy(
                Dish::isVegetarian,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparing(Dish::getCalories)),
                        Optional::get
                )
        ));
    }

    //
    private static Map<Boolean, Map<Boolean,List<Dish>>> multistagePartitioned(){
        return Dish.menu.stream().collect(Collectors.partitioningBy(
                Dish::isVegetarian,
                Collectors.partitioningBy(
                        (Dish dish)->dish.getCalories()>500
                )
        ));
    }
    private static Map<Boolean,Long> multistagePartitioned1(){
        return Dish.menu.stream().collect(Collectors.partitioningBy(
                Dish::isVegetarian,
                Collectors.counting()
        ));
    }
}
