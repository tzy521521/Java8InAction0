package chap3;

import java.util.function.DoubleFunction;

/**
 * Created by tzy on 2017/7/21.
 */
public class Math_think {
    public static void main(String[] args) {
        DoubleFunction<Double> f=(double x)->x+10;
        System.out.println(integrate(f,3,7));
    }
    public static double integrate(DoubleFunction<Double> f,double a,double b){
        return (f.apply(a)+f.apply(b))*(b-a)/2.0;
    }
}
