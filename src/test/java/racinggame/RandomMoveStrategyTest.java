package racinggame;

import nextstep.utils.Randoms;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import racinggame.strategy.MoveStrategy;
import racinggame.strategy.RandomMoveStrategy;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;

@DisplayName("RandomMoveStrategy 클래스")
class RandomMoveStrategyTest {
    private static MockedStatic<Randoms> mRandoms;
    private MoveStrategy moveStrategy;

    @BeforeAll
    public static void beforeClass() {
        mRandoms = mockStatic(Randoms.class);
    }

    @AfterAll
    public static void afterClass() {
        mRandoms.close();
    }

    @BeforeEach
    void setUp() {
        moveStrategy = new RandomMoveStrategy();
    }

    @DisplayName("movable 메서드는")
    @Nested
    class Describe_movable {
        @DisplayName("임의의 숫자로 3 이하가 나왔을 경우")
        @Nested
        class Context_with_three_under_point {

            @BeforeEach
            void setUp() {
                given(Randoms.pickNumberInRange(anyInt(), anyInt()))
                        .willReturn(new Random().nextInt(3));
            }

            @DisplayName("거짓을 반환한다.")
            @Test
            void movableWith3UnderPoint() {
                for (int i = 0; i < 4; i++) {
                    assertThat(moveStrategy.movable()).isFalse();
                }
            }
        }

        @DisplayName("임의의 숫자로 4 이상이 나왔을 경우")
        @Nested
        class Context_with_four_over_point {

            @BeforeEach
            void setUp() {
                given(Randoms.pickNumberInRange(anyInt(), anyInt()))
                        .willReturn(new Random().nextInt(5) + 4);

            }

            @DisplayName("참을 반환한다.")
            @Test
            void movableWith4OverPoint() {
                for (int i = 0; i < 10; i++) {
                    assertThat(moveStrategy.movable()).isTrue();
                }
            }
        }
    }

}