package chap5;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by tzy on 2017/7/22.
 */
public class BuildingStreams {
    public static void main(String[] args) {
        // 由值穿建流：静态方法Stream.of，通过显示值创建一个流。可以接受任意数量的参数。
        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // 由值穿建流：静态方法Stream.empty()创建一个空流
        Stream<String> emptyStream = Stream.empty();

        //由数组创建流:Arrays.stream(),如：将一个原始类型的int数组转换成一个IntStream。
        int[] numbers = {2, 3, 5, 7, 11, 13};
        System.out.println(Arrays.stream(numbers).sum());

        //由文件生成流:查看一个文件有多少个各不相同的词。
        long uniquewords=0;
        try(Stream<String> lines= Files.lines(Paths.get("./data.txt"), Charset.defaultCharset())){
            uniquewords=lines
                    .flatMap((String line)->Arrays.stream(line.split("")))
                    .distinct()
                    .count();
            System.out.println(uniquewords);

        }catch (IOException e){
            e.printStackTrace();
        }

        //Stream.iterate()和Stream.generate()可以从函数中创建一个无限流
        Stream.iterate(0,integer -> integer+2)
                .limit(10)
                .forEach(System.out::println);

        // fibonnaci with iterate
        Stream.iterate(new int[]{0, 1}, (int[] t) -> new int[]{t[1],t[0] + t[1]})
                .limit(10)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));

        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1],t[0] + t[1]})
                .limit(10)
                . map(t -> t[0])
                .forEach(System.out::println);

        // 生成：random stream of doubles with Stream.generate
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        // stream of 1s with Stream.generate
        IntStream.generate(() -> 1)
                .limit(5)
                .forEach(System.out::println);

        IntStream.generate(new IntSupplier(){
            public int getAsInt(){
                return 2;
            }
        }).limit(5)
                .forEach(System.out::println);

        IntSupplier fib = new IntSupplier(){
            private int previous = 0;
            private int current = 1;
            public int getAsInt(){
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return this.previous;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
