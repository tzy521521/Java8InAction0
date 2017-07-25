package chap8;

import chap4.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by tzy on 2017/7/25.
 */
public class Lambda_Reference {
    enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String[] args) {


        //将Lambda表达式的内容抽取到一个单独的方法中去：在Dish中添加getCaloricLevel()方法
        Map<CaloricLevel,List<Dish>> dishesByCaloricLevel=Dish.menu.stream()
                .collect(Collectors.groupingBy(
                        (Dish dish)->{
                            if (dish.getCalories()<=400) return CaloricLevel.DIET;
                            else if(dish.getCalories()<=700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        }
                ));
        System.out.println(dishesByCaloricLevel);
        Map<Dish.CaloricLevel,List<Dish>> dishesByCaloricLevel1=Dish.menu.stream().collect(Collectors.groupingBy(Dish::getCaloricLevel));
        System.out.println(dishesByCaloricLevel1);
    }
}
