package chap10;

import java.util.Optional;

/**
 * Created by tzy on 2017/7/25.
 */
public class OptionalMain {
    public static void main(String[] args) {

    }
    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}
