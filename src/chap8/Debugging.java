package chap8;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tzy on 2017/7/25.
 */
public class Debugging {
    public static void main(String[] args) {
        List<Point> points = Arrays.asList(new Point(12, 2), null);
        //points.stream().map(p -> p.getX()).forEach(System.out::println);
        points.stream().map(Point::getX).forEach(System.out::println);
    }


    private static class Point{
        private int x;
        private int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }
    }
}
