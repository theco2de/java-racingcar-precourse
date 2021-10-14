package racinggame.controller;

import racinggame.service.CarRacingService;
import racinggame.domain.dto.CarRacingResultData;
import racinggame.view.CarRacingView;

/**
 * 자동차 경주 게임에 대한 사용자의 요청을 처리한다.
 */
public class CarRacingController {
    private final CarRacingService carRacingService;
    private final CarRacingView carRacingView;

    public CarRacingController(CarRacingService carRacingService, CarRacingView carRacingView) {
        this.carRacingService = carRacingService;
        this.carRacingView = carRacingView;
    }

    /**
     * 자동차 경주에 필요한 정보를 요구하고 이를 콘솔에 출력한다.
     */
    public void startGame() {
        final String carNames = carRacingView.requestCarNames();
        final int round = carRacingView.requestRound();

        final CarRacingResultData resultData = carRacingService.carRacing(carNames, round);

        carRacingView.drawRacingProgress(resultData);
    }
}