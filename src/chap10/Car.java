package chap10;

import java.util.Optional;

/**
 * Created by tzy on 2017/7/25.
 */
public class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
