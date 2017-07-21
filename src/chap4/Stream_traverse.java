package chap4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by tzy on 2017/7/21.
 * 流只能遍历一次
 */
public class Stream_traverse {
    public static void main(String[] args) {
        List<String> title= Arrays.asList("Java","in","Action");
        Stream<String> s=title.stream();
        s.forEach(System.out::println);
        //s.forEach(System.out::println);
    }
}
