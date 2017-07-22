package chap5;

import chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by tzy on 2017/7/22.
 */
public class Searching {
    public static void main(String[] args) {
        Optional<Dish> dish=Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();

        Optional<Dish> dish1=Dish.menu.stream()
                .filter((Dish d)->d.getCalories()>1000)
                .findAny();

        System.out.println(dish.isPresent());
        System.out.println(dish1.isPresent());

        System.out.println(dish.get());
        //System.out.println(dish1.get());//值不存在时，会抛出一个NoSuchElement异常。

        System.out.println(dish.orElse(new Dish("pork", false, 800, Dish.Type.MEAT)));
        System.out.println(dish1.orElse(new Dish("pork", false, 800, Dish.Type.MEAT)));

        dish.ifPresent((Dish d)-> System.out.println());
        dish1.ifPresent((Dish d)-> System.out.println(d.getName()));//不执行代码

        List<Integer> someNumbers= Arrays.asList(1,2,3,4,5);
        Optional<Integer> firstSquareDivisibleByThree=someNumbers.stream()
                .map(integer -> integer*integer)
                .filter(integer -> integer%3==0)
                .findFirst();
        System.out.println(firstSquareDivisibleByThree);
    }
}
