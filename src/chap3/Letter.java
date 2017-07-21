package chap3;

import java.util.function.Function;

/**
 * Created by tzy on 2017/7/21.
 */
public class Letter {
    public static void main(String[] args) {
        Function<String,String> addheader=Letter::addFooter;
        Function<String,String> transformation =addheader.andThen(Letter::addFooter).andThen(Letter::checkSpelling);

        System.out.println(transformation.apply("Thank you! labda !!!!!"));
    }
    public static String addHeader(String text){
        return "From Roal,Mario and Alan: "+text;
    }
    public static String addFooter(String text){
        return text +"Kind regards";
    }
    public static String checkSpelling(String text){
        return text.replaceAll("labda","lambda");
    }
}
