package chap10;

import java.util.Optional;

/**
 * Created by tzy on 2017/7/25.
 */
public class Person {
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }
}
