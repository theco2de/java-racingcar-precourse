package racinggame;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racinggame.validation.DistanceValidator;
import racinggame.validation.NameValidator;
import racinggame.validation.Validators;
import racinggame.domain.Car;
import racinggame.domain.Distance;
import racinggame.domain.Name;
import racinggame.domain.RacingCar;
import racinggame.exception.InvalidDistanceException;
import racinggame.exception.InvalidNameException;
import racinggame.strategy.MoveStrategy;
import racinggame.strategy.RandomMoveStrategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("RacingCar 클래스")
class RacingCarTest {

    @BeforeEach
    void setUp() {
        Validators.addValidator(Name.class, new NameValidator());
        Validators.addValidator(Distance.class, new DistanceValidator());

    }


    @DisplayName("of 메서드는")
    @Nested
    class Describe_of {
        @DisplayName("이름과 거리가 모두 유효한 경우, 자동차 객체를 생성해 반환한다.")
        @ParameterizedTest
        @CsvSource(value = {"crong:0", "titi:2", "babi:3", "ppom:5"}, delimiter = ':')
        void ofWithValidData(String name, long distance) {
            final RacingCar racingCar = RacingCar.of(name, distance);

            assertThat(racingCar.getDistance()).isEqualTo(Distance.from(distance));
            assertThat(racingCar.getName()).isEqualTo(Name.from(name));
        }

        @DisplayName("이름이 유효하지 않고 거리는 유효한 경우, 예외를 던진다.")
        @ParameterizedTest
        @CsvSource(value = {"catsbi:3", "tikitaka:0", "'':1", "papapapapa:2"}, delimiter = ':')
        void ofWithInvalidName(String name, long distance) {
            assertThatThrownBy(() -> RacingCar.of(name, distance))
                    .isInstanceOf(InvalidNameException.class)
                    .hasMessage(String.format(InvalidNameException.DEFAULT_MESSAGE_FORMAT, name));
        }

        @DisplayName("이름은 유효하지만, 거리가 유효하지 않은 경우, 예외를 던진다.")
        @ParameterizedTest
        @CsvSource(value = {"crong:-1", "crong:-9999", "crong:-938591923939"}, delimiter = ':')
        void ofWithInvalidDistance(String name, long distance) {
            assertThatThrownBy(() -> RacingCar.of(name, distance))
                    .isInstanceOf(InvalidDistanceException.class)
                    .hasMessage(String.format(InvalidDistanceException.DEFAULT_MESSAGE_FORMAT, distance));
        }

        @DisplayName("이름과 거리가 모두 유효하지 않은 경우, 이름에 대한 예외를 던진다.")
        @ParameterizedTest
        @CsvSource(value = {"catsbi:-3", "tikitaka:-912930", "'':-1", "papapapapa:-2"}, delimiter = ':')
        void ofWithInvalidNameAndDistance(String name, long distance) {
            assertThatThrownBy(() -> RacingCar.of(name, distance))
                    .isInstanceOf(InvalidNameException.class)
                    .hasMessage(String.format(InvalidNameException.DEFAULT_MESSAGE_FORMAT, name));
        }

    }

    @DisplayName("move 메서드는")
    @Nested
    class Describe_move {
        private MoveStrategy moveStrategy;
        private Car racingCar;

        @BeforeEach
        void setUp() {
            moveStrategy = mock(RandomMoveStrategy.class);
            racingCar = RacingCar.of("test", 0);
        }


        @DisplayName("전진 전략이 참을 반환하는 경우")
        @Nested
        class Context_with_return_true {

            @BeforeEach
            void prepareMoveTest() {
                given(moveStrategy.movable()).willReturn(true);
            }

            @DisplayName("앞으로 전진한다.")
            @RepeatedTest(50)
            void moveWithStrategyReturnTrue() {
                racingCar.move(moveStrategy);

                assertThat(racingCar.getDistance()).isEqualTo(Distance.from(1));
            }
        }

        @DisplayName("전진 전략이 거짓을 반환하는 경우")
        @Nested
        class Context_with_return_false {
            @BeforeEach
            void prepareMoveTest() {
                given(moveStrategy.movable()).willReturn(false);
            }

            @DisplayName("그대로 멈춰있는다.")
            @RepeatedTest(50)
            void moveWithStrategyReturnTrue() {
                racingCar.move(moveStrategy);

                assertThat(racingCar.getDistance()).isEqualTo(Distance.from(0));
            }

        }

    }
}