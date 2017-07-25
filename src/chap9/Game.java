package chap9;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tzy on 2017/7/25.
 */
public class Game {
    public static void main(String...args){
        List<Resizable> resizableShapes = Arrays.asList(new Square(), new Triangle(), new Ellipse());
        Utils.paint(resizableShapes);
        System.out.println(resizableShapes);
    }
}
