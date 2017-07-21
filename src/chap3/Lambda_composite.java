package chap3;

import chap2.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by tzy on 2017/7/21.
 * 有些函数式接口中的都有几个用来复合Lambda表达式的默认方法。
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


        //谓词复合
        Predicate<Apple> reaApple=(Apple apple)->apple.getColor().equals("red");
        List<Apple> notRedApples = filterAbstract(inventory,reaApple.negate());
        System.out.println(notRedApples);

        //函数复合
        Function<Integer,Integer> f=x->x+1;
        Function<Integer,Integer> g=x->x*2;
        Function<Integer,Integer> h=f.andThen(g);
        Function<Integer,Integer> k=f.compose(g);
        /*
        int result=f.apply(1);
        System.out.println(result);
         */
        int result=h.apply(1);
        System.out.println(result);
        int result1=k.apply(1);
        System.out.println(result1);
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
