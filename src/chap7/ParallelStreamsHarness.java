package chap7;

import java.util.function.Function;

/**
 * Created by tzy on 2017/7/23.
 */
public class ParallelStreamsHarness {
    public static void main(String[] args) {
        //System.out.println("Iterative Sum done in: " + measurePerf(ParallelStreams::iterativeSum, 10_000_000L) + " msecs");
        //System.out.println("Sequential Sum done in: " + measurePerf(ParallelStreams::sequentialSum, 10_000_000L) + " msecs");

        //System.out.println("rangedSum Sum done in: " + measurePerf(ParallelStreams::rangedSum, 10_000_000L) + " msecs");
        //System.out.println("rangedSum Sum done in: " + measurePerf(ParallelStreams::parallelRangedSum, 10_000_000L) + " msecs");
    }
    public static  <T,R>long measurePerf(Function<T,R> adder,T n){
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i <5 ; i++) {
            long start = System.nanoTime();
            R sum =adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            //System.out.print("Result: " + sum+"/");
            System.out.print("Time: " + duration+"/");
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }
}
