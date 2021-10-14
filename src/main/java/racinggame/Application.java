package racinggame;

import racinggame.config.CarRacingConfig;
import racinggame.controller.CarRacingController;

public class Application {
    public static void main(String[] args) {
        // TODO 자동차 경주 게임 구현
        final CarRacingConfig config = new CarRacingConfig();
        final CarRacingController controller = config.carRacingController();

        controller.startGame();
    }
}