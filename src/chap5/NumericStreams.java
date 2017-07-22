package chap5;

import java.util.stream.IntStream;

/**
 * Created by tzy on 2017/7/22.
 * 5.6
 */
public class NumericStreams {
    public static void main(String[] args) {

        /*
        Java8引入了可以用于IntStream和LangStream的静态方法：rangeClosed()和range()。
        第一个参数接受初始值，第二个参数接受结束值。rangeClosed()包含结束值。
         */

        IntStream evenNumbers = IntStream.rangeClosed(1,100)
                .filter((int n)->n%2==0);
        System.out.println(evenNumbers.count());
    }
}
