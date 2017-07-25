package chap9;

/**
 * Created by tzy on 2017/7/25.
 */
public interface Moveable {
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
    default void moveHorizontally(int distance){
        setX(getX()+distance);
    }
    default void moveVertically(int distace){
        setY(getY()+distace);
    }
}
