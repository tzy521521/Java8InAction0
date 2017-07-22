package chap5;

import chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by tzy on 2017/7/21.
 * 5.2
 */
public class Mapping {
    public static void main(String[] args) {
        //map() 对流中的每一个元素应用函数
        List<String> dishNames = Dish.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(dishNames);

        //map() 对流中的每一个元素应用函数
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(wordLengths);

        //map() 对流中的每一个元素应用函数
        List<Integer> dishNamesLengths = Dish.menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(dishNamesLengths);

        //流的扁平化5.2.2
        List<String[]> result = words.stream()
                .map((String word)->word.split(""))//返回流实际上是Stream<String[]>
                .distinct()
                .collect(Collectors.toList());
        for (String[] temp:result) {
            for (int i = 0; i <temp.length ; i++) {
                System.out.print(temp[i]+" ");
            }
            System.out.println();
        }

        List<Stream<String>> result1 = words.stream()
                .map((String word)->word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        for (Stream<String> temp:result1) {
            temp.forEach((String s)->System.out.print(s+" "));
            System.out.println();
        }

        List<String> result2 = words.stream()
                .map((String word)->word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result2);
    }
}
