package chap9;

/**
 * Created by tzy on 2017/7/25.
 *
 */
public class Ambiguous {
    public static void main(String... args) {

        //1.类中的方法优先级别最高。
        new D().hello();
        //2.如果无法依据第一条进行判断，那么子接口的优先级最高。
        new E().hello();
        //如无法判断，继承了多个接口的类，必须通过显示覆盖和调用期望的方法。
        new F().hello();

        //
        new I().hello();

    }
    static interface A{
        public default void hello() {
            System.out.println("Hello from A");
        }
    }
    static interface B extends A {
        public default void hello() {
            System.out.println("Hello from B");
        }
    }
    static interface G extends A {

    }
    static interface H extends A {

    }
    static interface C {
        public default void hello() {
            System.out.println("Hello from C");
        }
    }
    static class D implements B, A {
        public void  hello(){
            System.out.println("Hello from D");
        }
    }

     static class E implements B, A {}

     /*
    类F无法通过编译。
     static class F implements C,A{

     }
      */
     //解决冲突
     static class F implements C,A{
         public void hello(){
             C.super.hello();
         }
     }
     //
    static class I implements G,H{

     }
}
