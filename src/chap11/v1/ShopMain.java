package chap11.v1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by tzy on 2017/7/26.
 */
public class ShopMain {
    public static void main(String[] args) {

        Shop shop = new Shop("BestShop");

        //使用同步API
        System.out.println("Strt Synchronous query price");
        long start = System.nanoTime();
        double price=shop.getPrice("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Product price$"+price+"  Blocking Invocation returned after " + invocationTime + " msecs");
        doSomethingElse();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //使用异步API
        System.out.println("Strt Asynchronous query price");
        long start1 = System.nanoTime();
        Future<Double> futurePrice=shop.getPriceAsync("my favorite product");
        long invocationTime1 = ((System.nanoTime() - start1) / 1_000_000);
        System.out.println("Product price$"+futurePrice+"  Non-Blocking Invocation returned after " + invocationTime1 + " msecs");
        doSomethingElse();
        long Async_start = System.nanoTime();
        try {
            double price_delpy = futurePrice.get();
            System.out.print(futurePrice+"  ");
            System.out.printf("Price is %.2f%n", price_delpy);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        long Async_retrievalTime = ((System.nanoTime() - Async_start) / 1_000_000);
        System.out.println("Price returned after " + Async_retrievalTime + " msecs");

    }
    private static void doSomethingElse() {
        System.out.println("Start doing something else...");
        long start = System.nanoTime();
        Util.delay();
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Time consuming "+invocationTime+" msecs ");
        System.out.println("Stop doing something else...");
    }
}
