package chap3;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * Created by tzy on 2017/7/20.
 */
public class Primitive_types {
    public static void main(String[] args) {
        int[] ls={1,2,3,4,5};

        int[] lsde = filterAbstract(ls,(Integer i)->i%2==0);
        for (int i = 0; i <lsde.length ; i++) {
            System.out.print(lsde[i]+" ");
        }
        System.out.println();
        int[] lsd= filterAbstract(ls,(int i)->i%2==0);
        for (int i = 0; i <lsd.length ; i++) {
            System.out.print(lsd[i]+" ");
        }
    }

    //有装箱操作
    public static int[] filterAbstract(int[] inventory, Predicate<Integer> p){
        int[] result =new int[inventory.length];
        for (int i = 0; i <inventory.length ; i++) {
            if(p.test(inventory[i])){
                result[i]=inventory[i];
            }
        }
        return result;
    }

    //无装箱操作
    public static int[] filterAbstract(int[] inventory, IntPredicate p){
        int[] result =new int[inventory.length];
        for (int i = 0; i <inventory.length ; i++) {
            if(p.test(inventory[i])){
                result[i]=inventory[i];
            }
        }
        return result;
    }
}
