package racinggame;

import Constant.Constant;
import domain.CarName;
import domain.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class carNameTest {

    private Condition condition;

    @BeforeEach
    void SetUp() {
        condition = new Condition();
    }

    @DisplayName("정상 자동차 이름 생성")
    @ParameterizedTest
    @ValueSource(strings = { "범블비", "이너셜D", "레이싱카"})
    void 정상_자동차_이름_테스트(String name) {
        CarName carName = new CarName(name);
        assertThat(carName.getName().length()).isLessThan(Constant.MAX_CAR_NAME);
    }

    @DisplayName("자동차 이름 에러 테스트")
    @ParameterizedTest
    @ValueSource(strings = { "", " ", "레이싱카 길이 테스트"})
    void 자동차_이름_에러_테스트(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> new CarName(name));
    }

}

