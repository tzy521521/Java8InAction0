package chap6;

import chap4.Dish;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by tzy on 2017/7/22.
 * 6.2~~~Collectors类中的静态工厂方法，可以返回收集器。
 */
public class Reduce_collector {
    public static void main(String[] args) {
        long howmanyDishes= Dish.menu.stream().collect(Collectors.counting());
        System.out.println(howmanyDishes);

        long howmanyDishes1=Dish.menu.stream().count();
        System.out.println(howmanyDishes1);

        //菜单中热量最高的菜
        Comparator<Dish> dishCaloriesComparator= Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish=Dish.menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        System.out.println(mostCalorieDish);

        //Collectors类专门为汇总提供工厂方法
        int totalCalories=Dish.menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        //
    }
}
