package chap6;

import chap4.Dish;

import java.util.stream.Collectors;

/**
 * Created by tzy on 2017/7/22.
 */
public class Summarizing {
    public static void main(String[] args) {
        System.out.println("Short menu: " + getShortMenu());
    }

    //joining()静态工厂方法返回的收集器会把流中每一个对象应用toSting()方法得到的所有字符串连接成一个字符串。
    private static String getShortMenu() {
        return Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
    }

    //
}
