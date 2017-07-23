package chap7;

import java.util.function.Function;

/**
 * Created by tzy on 2017/7/23.
 */
public class ParallelStreamsHarness {
    public static void main(String[] args) {

        //用传统for循环的迭代版本执行：更底层，不需要对原始类型做装箱操作。
        /*
        System.out.println("Iterative Sum done in: " + measurePerf(
                (Long n)->{
                    long result = 0;
                    for (long i = 0; i <= n; i++) {
                        result += i;
                    }
                    return result;
                }
                , 10_000_000L) + " msecs"
        );
         */
        System.out.println("Iterative Sum done in: " + measurePerf(ParallelStreams::iterativeSum, 10_000_000L) + " msecs");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Sequential Sum done in: " + measurePerf(ParallelStreams::sequentialSum, 10_000_000L) + " msecs");
        System.out.println("Sequential0 Sum done in: " + measurePerf(ParallelStreams::sequentialSum0, 10_000_000L) + " msecs");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Parallel forkJoinSum done in: " + measurePerf(ParallelStreams::parallelSum, 10_000_000L) + " msecs" );
        System.out.println("Parallel forkJoinSum done in: " + measurePerf(ParallelStreams::parallelSum0, 10_000_000L) + " msecs" );
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Range forkJoinSum done in: " + measurePerf(ParallelStreams::rangedSum, 10_000_000L) + " msecs" );
        System.out.println("Range forkJoinSum done in: " + measurePerf(ParallelStreams::rangedSum0, 10_000_000L) + " msecs" );
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("ParallelRange forkJoinSum done in: " + measurePerf(ParallelStreams::parallelRangedSum, 10_000_000L) + " msecs" );
        System.out.println("ParallelRange forkJoinSum done in: " + measurePerf(ParallelStreams::parallelRangedSum0, 10_000_000L) + " msecs" );
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("SideEffect sum done in: " + measurePerf(ParallelStreams::sideEffectSum, 10_000_000L) + " msecs" );
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("SideEffect prallel sum done in: " + measurePerf(ParallelStreams::sideEffectParallelSum, 10_000_000L) + " msecs" );
    }
    public static  <T,R>long measurePerf(Function<T,R> adder,T n){
        long fastest = Long.MAX_VALUE;
        Object sum=null;
        for (int i = 0; i <5 ; i++) {
            long start = System.nanoTime();
            sum =adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.print("Time: " + duration+"/ ");
            if (duration < fastest) fastest = duration;
        }
        System.out.print("Result: " +sum+" ");
        return fastest;
    }
}
