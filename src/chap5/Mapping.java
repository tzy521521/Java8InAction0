package chap5;

import chap4.Dish;
import chap4.Stream_iteration;

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
        List<String> dishNames = Dish.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(dishNames);

        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(wordLengths);

        List<Integer> dishNamesLengths = Dish.menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(dishNamesLengths);

        //流的扁平化5.2.2
        List<String[]> result = words.stream()
                .map((String word)->word.split(""))
                .distinct().collect(Collectors.toList());
                //.forEach(System.out::println);
        for (String[] temp:result) {
            for (int i = 0; i <temp.length ; i++) {
                System.out.print(temp[i]+" ");
            }
            System.out.println();
        }
        //

        List<Stream<String>> result1 = words.stream()
                .map((String word)->word.split(""))
                .map(Arrays::stream)
                .distinct().collect(Collectors.toList());
                //.forEach(System.out::println);
        for (Stream<String> temp:result1) {
            temp.forEach(System.out::println);
        }
        /*
        words.stream()
                .map((String word)->word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);
         */
    }
}
