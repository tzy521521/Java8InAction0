package chap2;

import java.util.function.Predicate;

/**
 * Created by tzy on 2017/7/20.
 */
public class AppleGreenColorPredicate implements Predicate<Apple>{
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
