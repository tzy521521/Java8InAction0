package chap4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tzy on 2017/7/21.
 * 4.3
 */
public class Stream_iteration {
    public static void main(String[] args) {
        //用for-each循环外部迭代
        List<String> names=new ArrayList<>();
        for (Dish d: Dish.menu) {
            names.add(d.getName());
        }
        System.out.println(names);

        //用背后的迭代器做外部迭代
        List<String> names1=new ArrayList<>();
        Iterator<Dish> iterator=Dish.menu.iterator();
        while (iterator.hasNext()){
            Dish d=iterator.next();
            names1.add(d.getName());
        }
        System.out.println(names1);

        //流内部迭代
        List<String> names2=Dish.menu.stream().map(Dish::getName).collect(Collectors.toList());
        System.out.println(names2);

    }
}
