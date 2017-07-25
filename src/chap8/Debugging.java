package chap8;

/**
 * Created by tzy on 2017/7/25.
 */
public class Debugging {
    public static void main(String[] args) {

    }
    private static class Point{
        private final int x;
        private final int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Point moveRightBy(int x){
            return new Point(this.x+x,this.y);
        }
    }
}
