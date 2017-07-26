package chap11.v1;

/**
 * Created by tzy on 2017/7/26.
 */
public class Util {
    //模拟1秒钟延迟的方法
    public static void delay() {
        int delay = 1000;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
