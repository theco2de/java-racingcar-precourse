package racinggame;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import racinggame.validation.NameValidator;
import racinggame.validation.Validators;
import racinggame.domain.Name;
import racinggame.exception.InvalidNameException;
import racinggame.exception.ParameterNullPointException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Name 클래스 테스트")
class NameTest {

    @BeforeEach
    void setUp() {
        Validators.addValidator(Name.class, new NameValidator());
    }

    @DisplayName("from 메서드는")
    @Nested
    class Describe_from {
        @DisplayName("5글자 이내의 이름인 경우, 정상적으로 생성되어 객체를 반환합니다.")
        @ParameterizedTest
        @ValueSource(strings = {"abc", "pobi", "bibi", "crong", "cicha", "bo", "b"})
        void fromWithValidName(String inputName) {

            final Name createdName = Name.from(inputName);

            assertThat(createdName)
                    .hasToString(inputName)
                    .isEqualTo(Name.from(inputName));
        }

        @DisplayName("이름이 5글자 초과 이거나 공백인 경우, 예외를 던집니다.")
        @ParameterizedTest
        @ValueSource(strings = {"", "catsbi", "fiveOver", "titititi", "appdfiduifnjhbalsdjkljgkhjasdfaslkdfjlskadfj"})
        void fromWithInvalidName(String inputName) {

            assertThatThrownBy(() -> Name.from(inputName))
                    .isInstanceOf(InvalidNameException.class)
                    .hasMessage(String.format(InvalidNameException.DEFAULT_MESSAGE_FORMAT, inputName));
        }

        @DisplayName("이름이 null인 경우, 예외를 던집니다.")
        @ParameterizedTest
        @NullSource
        void fromWithNullName(String inputName) {
            assertThatThrownBy(() -> Name.from(inputName))
                    .isInstanceOf(InvalidNameException.class)
                    .hasMessage(ParameterNullPointException.DEFAULT_MESSAGE);
        }

    }

}