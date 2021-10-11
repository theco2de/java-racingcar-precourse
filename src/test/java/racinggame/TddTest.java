package racinggame;

import domain.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TddTest {

    @Test
    @DisplayName("자동차 설계")
    void mainTest() {
        new Car();
    }
}
