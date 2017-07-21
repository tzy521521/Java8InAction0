package chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by tzy on 2017/7/20.
 */
public class PredicateTest {
    public static void main(String[] args) {

        Predicate<String> nonEmptyStringPredicate = (String s)->!s.isEmpty();

        List<String> listofStrings= Arrays.asList("dfsd","sdfsadf","","fasdf","","sdfas");
        List<String> nonEmpty = filter(listofStrings,nonEmptyStringPredicate);
        System.out.println(nonEmpty);
    }
    public static <T>List<T> filter(List<T> list, Predicate<T> p){
        List<T> result=new ArrayList<>();
        for (T s:list) {
            if (p.test(s))
                result.add(s);
        }
        return result;
    }
}
