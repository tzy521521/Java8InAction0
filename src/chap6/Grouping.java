package chap6;

import chap4.Dish;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tzy on 2017/7/23.
 * 6.3
 */
public class Grouping {
    enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String[] args) {
        System.out.println("Dishes grouped by type: " + groupDishesByType());
        System.out.println("Dishes grouped by caloric level: " + groupDishesByCaloricLevel());
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel());
        System.out.println("Count dishes in groups: " + countDishesInGroups());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByType());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByTypeWithoutOprionals());
        System.out.println("Sum calories by type: " + totalCaloriesByType());
        System.out.println("Caloric levels by type: " + caloricLevelsByType());
    }
    //分组:
    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
        return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));
    }

    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel(){
        return Dish.menu.stream().collect(Collectors.groupingBy((Dish dish)->{
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }));
    }
    //分组:可以有效的组合。双参数版本的的Collectors.groupingBy(),除了接受一个普通分类函数之外，还可以接受collector类型的第二个参数参数
    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel(){
        return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.groupingBy((Dish dish)->{
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        })));
    }
    //传递给第一个Collectors.groupingBy()的第二个收集器可以是任何类型。
    private static Map<Dish.Type, Long> countDishesInGroups() {
        return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
    }

    //
    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType,
                //Collectors.reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))
                ));
    }
    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOprionals() {
        return Dish.menu.stream().collect(Collectors.groupingBy(
                Dish::getType,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),Optional::get)
        ));
    }

    //
    private static Map<Dish.Type, Integer> totalCaloriesByType() {
        return Dish.menu.stream().collect(Collectors.groupingBy(
                Dish::getType,
                Collectors.summingInt(Dish::getCalories)
        ));
    }

    //
    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType(){
        return Dish.menu.stream().collect(Collectors.groupingBy(
                Dish::getType,
                Collectors.mapping(
                        (Dish dish)->{
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;},
                        //Collectors.toSet()
                        Collectors.toCollection(HashSet::new)
                )
        ));
    }
}
