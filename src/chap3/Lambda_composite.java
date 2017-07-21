package chap3;

import chap2.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by tzy on 2017/7/21.
 */
public class Lambda_composite {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80,"green"),
                new Apple(160, "green"),
                new Apple(155, "red"),
                new Apple(155, "green")
        );

        //逆序
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
        System.out.println(inventory);

        //比较器链
        inventory.sort(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getColor));
        System.out.println(inventory);


        Predicate<Apple> reaApple=(Apple apple)->apple.getColor().equals("red");
        List<Apple> notRedApples = filterAbstract(inventory,reaApple.negate());//谓词复合
        System.out.println(notRedApples);
    }
    //将List类型抽象化
    public static <T>List<T> filterAbstract(List<T> inventory, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T e : inventory){
            if(p.test(e)){
                result.add(e);
            }
        }
        return result;
    }
}
