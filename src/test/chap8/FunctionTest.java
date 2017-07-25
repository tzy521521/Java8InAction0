package test.chap8;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by tzy on 2017/7/25.
 */
public class FunctionTest {
    public static <T>List<T> filter(List<T> inventory, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T e : inventory){
            if(p.test(e)){
                result.add(e);
            }
        }
        return result;
    }
    @Test
    public void testFilter()throws Exception{
        List<Integer> numbers= Arrays.asList(1,2,3,4);
        List<Integer> even=filter(numbers,integer -> integer%2==0);
        List<Integer> smallerThanTree=filter(numbers,integer -> integer<4);

        Assert.assertEquals(Arrays.asList(2,4),even);
        Assert.assertEquals(Arrays.asList(1,2),smallerThanTree);
    }
}
