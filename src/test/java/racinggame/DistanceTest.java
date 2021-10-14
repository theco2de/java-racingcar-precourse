package racinggame;


import nextstep.utils.Randoms;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racinggame.validation.DistanceValidator;
import racinggame.validation.Validators;
import racinggame.domain.Distance;
import racinggame.exception.InvalidDistanceException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Distance 클래스")
class DistanceTest {

    @BeforeEach
    void setUp() {
        Validators.addValidator(Distance.class, new DistanceValidator());
    }

    @DisplayName("from 메서드는")
    @Nested
    class Describe_from {
        @DisplayName("0 이상인 값을 전달하는 경우, 새로운 Distance 객체를 만들어 반환한다.")
        @ParameterizedTest
        @ValueSource(longs = {0, 1, 2, 3, 4, 5, 6, 7, Long.MAX_VALUE})
        void fromWithValidData(long value) {
            final Distance distance = Distance.from(value);

            assertThat(distance)
                    .hasToString(value + "")
                    .isEqualTo(Distance.from(value));
        }

        @DisplayName("0 미만의 값을 전달하는 경우, 예외를 던진다.")
        @ParameterizedTest
        @ValueSource(longs = {Long.MIN_VALUE, -99L, -9999L, -1L, -1235874L})
        void fromWithInvalidData(long value) {
            assertThatThrownBy(() -> Distance.from(value))
                    .isInstanceOf(InvalidDistanceException.class)
                    .hasMessage(String.format(InvalidDistanceException.DEFAULT_MESSAGE_FORMAT, value));
        }
    }

    @DisplayName("of 메서드는")
    @Nested
    class Describe_of {
        private Distance distance;

        @BeforeEach
        void setUp() {
            distance = Distance.from(5);
        }

        @DisplayName("두 인자의 거리 합이 0 이상인 경우, 새로운 Distance 객체를 만들어 반환한다.")
        @RepeatedTest(50)
        void ofWithValidData() {
            final int value = Randoms.pickNumberInRange(0, 10);

            final Distance newDistance = Distance.of(value, distance);

            assertThat(newDistance != distance).isTrue();
            assertThat(newDistance).hasToString(5 + value + "");
        }

        @DisplayName("두 인자의 거리 합이 0 미만인 경우, 예외를 던진다.")
        @ParameterizedTest
        @ValueSource(longs = {Long.MIN_VALUE, -99L, -9999L, -6L, -1235874L})
        void ofWithInvalidData(long value) {
            assertThatThrownBy(() -> Distance.of(value, distance))
                    .isInstanceOf(InvalidDistanceException.class)
                    .hasMessage(String.format(InvalidDistanceException.DEFAULT_MESSAGE_FORMAT, value + 5));
        }
    }
}