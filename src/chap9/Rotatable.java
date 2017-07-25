package chap9;

/**
 * Created by tzy on 2017/7/25.
 */
public interface Rotatable {
    void setRotatableAngle(int angleInDegrees);
    int getRotationAngle();
    default void rotateBy(int angleInDegrees){
        setRotatableAngle((getRotationAngle()+angleInDegrees)%360);
    }
}
