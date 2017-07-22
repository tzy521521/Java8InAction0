package chap6;

import chap4.Dish;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by tzy on 2017/7/22.
 * 6.2.4
 */
public class Reducing {
    public static void main(String[] args) {
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Most calorie Dish in menu: "+mostCalorieDish().get().getName());
    }

    //
    private static int calculateTotalCalories() {
        return Dish.menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j));
    }

    //单参数形式的reducing找到热量最高的菜。
    private static Optional<Dish> mostCalorieDish(){
        return Dish.menu.stream().
                collect(Collectors.reducing((Dish d1, Dish d2)->d1.getCalories()>d2.getCalories()?d1:d2));
    }
}
