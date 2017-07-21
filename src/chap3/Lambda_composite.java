package chap3;

import chap2.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by tzy on 2017/7/21.
 */
public class Lambda_composite {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80,"green"),
                new Apple(160, "green"),
                new Apple(155, "red")
        );

        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
        System.out.println(inventory);
    }
}
