package chap2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by tzy on 2017/7/20.
 * 行为参数化，是一个很有用的模式。这种模式把一个行为（或者一段代码）封装起来，并通过传递和使用创建的行为
 * 把方法的行为参数化。这种做法类似于策略模式。
 */
public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80,"green"),
                new Apple(160, "green"),
                new Apple(155, "red")
        );

        //第4次尝试:根据抽象条件筛选
        List<Apple> heavyApples = filter(inventory, new AppleHeavyWeightPredicate());
        System.out.println(heavyApples);

        List<Apple> greenApples = filter(inventory, new AppleGreenColorPredicate());
        System.out.println(greenApples);

        List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
        System.out.println(redAndHeavyApples);
        //需要声明很多只需要实例化一次的类~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        //第5次尝试:创建一个匿名类来实现Predicate对象
        List<Apple> redApples = filter(inventory, new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor().equals("red");
            }
        });
        System.out.println(redApples);

        //第6次尝试:使用Lambda表达式
        List<Apple> redApples2 = filter(inventory,(Apple apple)->apple.getColor().equals("red") );
        System.out.println(redApples2);

        //第7次尝试:将List抽象化
        List<Apple> redApples3 = filterAbstract(inventory,(Apple apple)->apple.getColor().equals("red") );
        System.out.println(redApples3);

        List<Integer> numbers = Arrays.asList(
                new Integer(1),new Integer(2),
                new Integer(3),new Integer(4),
                new Integer(5)
        );
        List<Integer> evenNumbers = filterAbstract(numbers,(Integer i)->i%2==0);
        System.out.println(evenNumbers);

        //Java的API中很多方法可以用不同的行为参数化。用Comparator来排序。
        inventory.sort((Apple apple1,Apple apple2)->apple1.getWeight().compareTo(apple2.getWeight()));
        System.out.println(inventory);
    }

    public static List<Apple> filter(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
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
