package chap9;

/**
 * Created by tzy on 2017/7/25.
 */
public interface Resizable {
    public int getWidth();
    public int getHeight();
    public void setWidth(int width);
    public void setHeight(int height);
    public void setAbsoluteSize(int width, int height);
    default public void setRelativeSize(int widthFactor, int heightFactor){
        setAbsoluteSize(getWidth()/widthFactor,getHeight()/heightFactor);
    }

}
