package chap5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by tzy on 2017/7/22.
 * 5.4
 */
public class Reducing {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);
        int sum = numbers.stream().reduce(0,(Integer a,Integer b)->a+b);//有装箱和拆箱操作。
        System.out.println(sum);
        //使用方法引用
        int sum1 = numbers.stream().reduce(0,Integer::sum);//有装箱和拆箱操作。
        System.out.println(sum1);

        //reduce的一个变体
        Optional<Integer> sum2=numbers.stream().reduce(Integer::sum);
        System.out.println(sum2);

        //最小值
        Optional<Integer> min =numbers.stream().reduce((integer, integer2) -> integer<integer2?integer:integer2);
        System.out.println(min);
        Optional<Integer> min1 =numbers.stream().reduce(Integer::min);
        System.out.println(min1);

        //最大值
        Optional<Integer> max =numbers.stream().reduce((integer, integer2) -> integer>integer2?integer:integer2);
        System.out.println(max);
        Optional<Integer> max2 =numbers.stream().reduce(Integer::max);
        System.out.println(max2);
    }
}
