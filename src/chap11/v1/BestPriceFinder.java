package chap11.v1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * Created by tzy on 2017/7/26.
 */
public class BestPriceFinder {
    private final List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy")
    );

    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(),100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    public List<String> findPricesSequential(String product){
        return shops.stream()
                .map((Shop shop)->String.format("%s price is %.2f",shop.getName(),shop.getPrice(product)))
                .collect(Collectors.toList());
    }
    //代码清单11-10并行流对请求进行并行操作
    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map((Shop shop)->String.format("%s price is %.2f",shop.getName(),shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map((Shop shop) -> CompletableFuture.supplyAsync(
                                ()->String.format("%s price is %.2f",shop.getName(),shop.getPrice(product),executor))
                        )
                        .collect(Collectors.toList());

        List<String> prices = priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        return prices;
    }
}
