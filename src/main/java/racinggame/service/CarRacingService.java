package racinggame.service;

import racinggame.domain.Cars;
import racinggame.domain.Round;
import racinggame.domain.dto.CarData;
import racinggame.domain.dto.CarRacingProgress;
import racinggame.domain.dto.CarRacingResultData;
import racinggame.strategy.MoveStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * 자동차 경주에 대한 서비스를 제공한다.
 */
public class CarRacingService {
    private final MoveStrategy moveStrategy;

    public CarRacingService(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    /**
     * 자동차 경주를 인자로 전달한 라운드만큼 진행하고 그 결과를 반환한다.
     *
     * @param names 참가할 경주 자동차 이름목록
     * @param roundNum 경주를 진행할 횟수
     * @return 자동차 경주 결과
     */
    public CarRacingResultData carRacing(String names, int roundNum) {
        List<CarRacingProgress> progressList = new ArrayList<>();
        final Cars cars = Cars.from(names);
        final Round round = new Round(roundNum);

        while (round.hasNext()) {
            cars.notifyAll(car -> car.move(moveStrategy));
            progressList.add(new CarRacingProgress(round.next(), cars.map(CarData::from)));
        }

        List<String> winners = cars.getWinners();

        return new CarRacingResultData(progressList, winners);
    }
}