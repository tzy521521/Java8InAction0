package chap11.v1;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by tzy on 2017/7/26.
 */
public class Shop {
    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public String getName() {
        return name;
    }

    //同步查询价格方法。
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    //异步查询价格的方法
    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> futurePrice=new CompletableFuture<>();
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                double price=calculatePrice(product);
                futurePrice.complete(price);
            }
        }).start();
         */
        new Thread(()->{
            try {
                double price=calculatePrice(product);
                futurePrice.complete(price);
            }catch (Exception ex){
                futurePrice.completeExceptionally(ex);
            }

        }).start();

        //无需等待还没结束的计算，直接返回Future对象。
        return futurePrice;
    }
    //代码清单11-7：使用工厂方法创建CompletableFuture对象。
    public Future<Double> getPriceAsync0(String product){
        return CompletableFuture.supplyAsync(()->calculatePrice(product));
    }

    private double calculatePrice(String product) {
        Util.delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }


}
