package chap7;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by tzy on 2017/7/23.
 */
public class ParallelStreams {
    //迭代求和。
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    //
    public static long sequentialSum(long n){
        return Stream.iterate(1L,(Long i)->i+1).limit(n).reduce(0L,Long::sum);
    }
    public static long sequentialSum0(long n){
        return Stream.iterate(1L,(Long i)->i+1).limit(n).reduce(Long::sum).get();
    }
    //
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(Long::sum).get();
    }

    //
    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(Long::sum).getAsLong();
    }

    public static long rangedSum1(long n) {
        return LongStream.rangeClosed(1, n).reduce(0L,Long::sum);
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(Long::sum).getAsLong();
    }

    public static long parallelRangedSum1(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(0L,Long::sum);
    }
}
