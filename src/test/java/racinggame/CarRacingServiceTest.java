package racinggame;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racinggame.validation.DistanceValidator;
import racinggame.validation.NameValidator;
import racinggame.validation.RoundValidator;
import racinggame.validation.Validators;
import racinggame.domain.Distance;
import racinggame.domain.Name;
import racinggame.domain.Round;
import racinggame.domain.dto.CarRacingProgress;
import racinggame.domain.dto.CarRacingResultData;
import racinggame.exception.InvalidNameException;
import racinggame.service.CarRacingService;
import racinggame.strategy.MoveStrategy;
import racinggame.strategy.RandomMoveStrategy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("CarRacingService 클래스")
class CarRacingServiceTest {

    private CarRacingService carRacingService;
    private MoveStrategy moveStrategy;

    @BeforeEach
    void setUp() {
        Validators.addValidator(Name.class, new NameValidator());
        Validators.addValidator(Distance.class, new DistanceValidator());
        Validators.addValidator(Round.class, new RoundValidator());

        moveStrategy = mock(RandomMoveStrategy.class);
        carRacingService = new CarRacingService(moveStrategy);
    }

    @DisplayName("carRacing 메서드는")
    @Nested
    class Describe_carRacing {

        @DisplayName("인자가 유효할 경우, 경기 결과를 반환한다.")
        @ParameterizedTest
        @CsvSource(value = {"crong,pobi,catbi,titi:2", "marvl,bora,catbi,level:3", "bbo,laro,niamo,sisol:5"}, delimiter = ':')
        void carRacingWithValidData(String names, int round) {
            given(moveStrategy.movable()).willReturn(true);
            final CarRacingResultData results = carRacingService.carRacing(names, round);

            assertThat(results.getProgressList().size()).isEqualTo(round);

            final List<CarRacingProgress> progresses = results.getProgressList();
            for (int i = 0; i < round; i++) {
                final CarRacingProgress progress = progresses.get(i);
                assertThatProgress(progress, i + 1L);
            }
        }

        private void assertThatProgress(CarRacingProgress progress, Long distance) {
            for (int i = 0; i < progress.size(); i++) {
                assertThat(progress.get(i).getDistance()).isEqualTo(distance);
            }
        }

        @DisplayName("이름이 유효하지 않을 경우, 예외를 던진다.")
        @ParameterizedTest
        @CsvSource(value = {"catsbi,ppooii:2", ",titi:5", ",, ,lala:6"}, delimiter = ':')
        void carRacingWithInvalidData(String names, int round) {
            given(moveStrategy.movable()).willReturn(true);

            assertThatThrownBy(() -> carRacingService.carRacing(names, round))
                    .isInstanceOf(InvalidNameException.class);


        }

    }

}