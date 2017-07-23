package chap6;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by tzy on 2017/7/23.
 * 6.4.2
 */
public class primePartition {
    public static void main(String[] args) {
        System.out.println("partitionPrime"+partitionPrime(50));
    }
    public static Map<Boolean,List<Integer>> partitionPrime(int n){
        return IntStream.rangeClosed(2,n).boxed().collect(Collectors.partitioningBy(
                (Integer x)->isPrime(x)
        ));

    }
    public static boolean isPrime(int canditdate){
        //待测数字不能被流中的任意一个数字整除，为质数
        //return IntStream.range(2,canditdate).noneMatch((int i)->canditdate%1==0);

        //优化
        int candidateRoot=(int)Math.sqrt(canditdate);
        return IntStream.range(2,candidateRoot).noneMatch((int i)->canditdate%1==0);
    }
}
