package racinggame;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racinggame.validation.RoundValidator;
import racinggame.validation.Validators;
import racinggame.domain.Round;
import racinggame.exception.InvalidRoundValueException;
import racinggame.exception.RoundOverProcessException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Round 클래스")
class RoundTest {

    @BeforeEach
    void setUp() {
        Validators.addValidator(Round.class, new RoundValidator());
    }

    @DisplayName("1이상의 진행 횟수로 생성 시도를 할 경우 정상적으로 객체가 생성된다.")
    @Test
    void constructRoundWithValidValue() {
        for (int i = 1; i < 100; i++) {
            final Round round = new Round(i);
            assertThat(round.next()).isEqualTo(1);
        }
    }

    @DisplayName("0이하의 진행 횟수로 생성 시도를 할 경우 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(ints = {-99, -999, -5848, -1, -2, 0})
    void constructRoundWithInvalidValue(int invalidValue) {

        assertThatThrownBy(() -> new Round(invalidValue))
                .isInstanceOf(InvalidRoundValueException.class)
                .hasMessage(String.format(InvalidRoundValueException.DEFAULT_MESSAGE_FORMAT, invalidValue));

    }

    @DisplayName("5라운드 객체에서 게임을 4번 진행하면, hasNext메서드는 참을 반환한다.")
    @Test
    void nextWithRemainRound() {
        final Round round = new Round(5);
        for (int i = 0; i < 4; i++) {
            round.next();
        }

        assertThat(round.hasNext()).isTrue();
    }

    @DisplayName("5라운드 객체에서 게임을 5번 진행하면, hasNext메서드는 거짓을 반환한다.")
    @Test
    void nextWithoutRemainRound() {
        final Round round = new Round(5);
        for (int i = 0; i < 5; i++) {
            round.next();
        }

        assertThat(round.hasNext()).isFalse();
    }

    @DisplayName("5라운드 객체에서 게임을 6번 진행하려 하면 예외를 던진다.")
    @Test
    void nextWithOverProgress() {
        final Round round = new Round(5);
        for (int i = 0; i < 5; i++) {
            round.next();
        }

        assertThatThrownBy(round::next)
                .isInstanceOf(RoundOverProcessException.class);
    }

    @DisplayName("next 메서드는 현재 진행 라운드 값을 반환한다.")
    @Test
    void next() {
        final Round round = new Round(5);

        for (int i = 1; i <= 5; i++) {
            assertThat(round.next()).isEqualTo(i);
        }
    }


}