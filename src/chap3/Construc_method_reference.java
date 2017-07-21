package chap3;

import chap2.Apple;
import chap2.Fruit;
import chap2.Orange;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by tzy on 2017/7/21.
 */
public class Construc_method_reference {
    public static void main(String[] args) {

        Supplier<Apple> c1=Apple::new;
        Apple apple1=c1.get();
        System.out.println(apple1);

        Function<Integer,Apple> c2= Apple::new;
        Apple apple2=c2.apply(140);
        System.out.println(apple2);

        BiFunction<Integer,String,Apple> c3=Apple::new;
        Apple apple3=c3.apply(160,"yello");
        System.out.println(apple3);

        //不讲构造函数实例化却能够引用他，这个功能有一个有趣的应用。可以用Map来将构造函数映射到字符串值。
        Orange orange=(Orange)giveMeFruit("Orange",100);
        System.out.println(orange);
        System.out.println(orange.getWeight());
    }
    static Map<String,Function<Integer, Fruit>> map =new HashMap<>();
    static {
        map.put("apple",Apple::new);
        map.put("orange", Orange::new);
    }
    public static Fruit giveMeFruit(String fruit,Integer weight){
        return map.get(fruit.toLowerCase()).apply(weight);
    }
}
