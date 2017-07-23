package chap6;

import chap4.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by tzy on 2017/7/22.
 * 6.2.4
 */
public class Reducing {
    public static void main(String[] args) {
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithMethodReference());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithoutCollectors());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesUsingSum());

        System.out.println("Most calorie Dish in menu: "+mostCalorieDish());

        //reduce()方法实现Collectors.toList()所做的工作。
        Stream<Integer> stream = Arrays.asList(1,2,3,4,5).stream();
        List<Integer> number=stream.reduce(new ArrayList<>(),(List<Integer> l,Integer e)->{l.add(e);return l;},(List<Integer> ll,List<Integer> l2)->{ll.addAll(l2);return ll;});
        System.out.println(number);
    }

    //收集框架的灵活性:以不同的方法执行同样的操作。
    private static int calculateTotalCalories() {
        return Dish.menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j));
    }
    private static int calculateTotalCaloriesWithMethodReference() {
        return Dish.menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
    }
    private static int calculateTotalCaloriesWithoutCollectors() {
        return Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
    }

    private static int calculateTotalCaloriesUsingSum() {
        return Dish.menu.stream().mapToInt(Dish::getCalories).sum();
    }
    //单参数形式的reducing找到热量最高的菜。
    private static Optional<Dish> mostCalorieDish(){
        return Dish.menu.stream().
                collect(Collectors.reducing((Dish d1, Dish d2)->d1.getCalories()>d2.getCalories()?d1:d2));
    }
}
