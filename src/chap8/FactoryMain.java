package chap8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by tzy on 2017/7/25.
 */
public class FactoryMain {
    public static void main(String[] args) {
        Product p1 = ProductFactory.createProduct("loan");
        System.out.println(p1);

        Supplier<Product> loanSupplier = Loan::new;
        Product p2 = loanSupplier.get();
        System.out.println(p2);

        Product p3 = ProductFactory.createProductLambda("loan");
        System.out.println(p3);
    }
    //金融产品接口
    static private interface Product {}
    //金融产品
    static private class Loan implements Product {}
    static private class Stock implements Product {}
    static private class Bond implements Product {}
    //
    static private class ProductFactory {
        public static Product createProduct(String name){
            switch(name){
                case "loan": return new Loan();
                case "stock": return new Stock();
                case "bond": return new Bond();
                default: throw new RuntimeException("No such product " + name);
            }
        }
        //
        public static Product createProductLambda(String name){
            Supplier<Product> p = map.get(name);
            if(p != null) return p.get();
            throw new RuntimeException("No such product " + name);
        }

        final static private Map<String, Supplier<Product>> map = new HashMap<>();
        static {
            map.put("loan", Loan::new);
            map.put("stock", Stock::new);
            map.put("bond", Bond::new);
        }
    }
}
