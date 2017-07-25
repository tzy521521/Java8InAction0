package chap8;

/**
 * Created by tzy on 2017/7/25.
 * 8.1.2
 */
public class Anonymous_Lambda {
    public static void main(String[] args) {
        int a=10;
        Runnable r1=new Runnable() {
            @Override
            public void run() {
                int a=22;
                System.out.println(a);
            }
        };
        r1.run();
        Runnable r2=()->{
            //int a=20;//匿名类可以屏蔽包含类的变量，但是Lambda表达式不能。
            System.out.println(a);
        };
        r2.run();
        System.out.println(a);

        //匿名类的类型是在初始化时确定的。
        dosomething(new Task() {
            @Override
            public void execute() {
                System.out.println("Danger danger");
            }
        });
        //Lambda:显示的类型转换
        dosomething((Runnable) ()->{
            System.out.println("Not sure");
        });
    }
    public static interface Task{
        public void execute();

    }
    public static void dosomething(Runnable runnable){
        runnable.run();
    }
    public static void dosomething(Task task){
        task.execute();
    }
}
