package chap2;

/**
 * Created by tzy on 2017/7/21.
 */
public class Orange implements Fruit{
    private int weight = 0;
    private String color = "";

    public Orange() {
    }

    public Orange(int weight) {
        this.weight = weight;
    }

    public Orange(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Orange{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
