package chap9;

import java.util.List;

/**
 * Created by tzy on 2017/7/25.
 */
public class Utils {
    public static void paint(List<Resizable> list){
        list.forEach((Resizable resizable)->resizable.setAbsoluteSize(42,42));
    }
}
