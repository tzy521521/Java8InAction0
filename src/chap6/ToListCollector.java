package chap6;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by tzy on 2017/7/23.
 */
public class ToListCollector<T> implements Collector<T,List<T>,List<T>>{
    @Override
    public Supplier<List<T>> supplier() {
        /*
        return new Supplier<List<T>>() {
            @Override
            public List<T> get() {
                return new ArrayList<>();
            }
        };
         */
        //return ()->new ArrayList<>();

        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        /*
        return new BiConsumer<List<T>, T>() {
            @Override
            public void accept(List<T> list, T t) {
                list.add(t);
            }
        };
         */
        //return (List<T> list, T t)->list.add(t);
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        /*
        return new BinaryOperator<List<T>>() {
            @Override
            public List<T> apply(List<T> list, List<T> list2) {
                list.addAll(list2);
                return list;
            }
        };
         */
        return (List<T> list, List<T> list2)->{list.addAll(list2);return list;};
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        /*
        return new Function<List<T>, List<T>>() {
            @Override
            public List<T> apply(List<T> list) {
                return list;
            }
        };
         */
        //return (List<T> list)->list;
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
