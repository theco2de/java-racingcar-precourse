package racinggame.config;

import racinggame.controller.CarRacingController;
import racinggame.domain.Distance;
import racinggame.domain.Name;
import racinggame.domain.Round;
import racinggame.service.CarRacingService;
import racinggame.strategy.MoveStrategy;
import racinggame.strategy.RandomMoveStrategy;
import racinggame.validation.DistanceValidator;
import racinggame.validation.NameValidator;
import racinggame.validation.RoundValidator;
import racinggame.validation.Validators;
import racinggame.view.*;

/**
 * 자동차 경주 게임 클래스 의존 관계 설정
 */
public class CarRacingConfig {

    public CarRacingConfig() {
        Validators.addValidator(Name.class, new NameValidator());
        Validators.addValidator(Distance.class, new DistanceValidator());
        Validators.addValidator(Round.class, new RoundValidator());
    }

    public CarRacingController carRacingController() {
        return new CarRacingController(carRacingService(), carRacingView());
    }

    private CarRacingView carRacingView() {
        return new CarRacingViewImpl(carRacingInputView(), carRacingOutputView());
    }

    private CarRacingOutputView carRacingOutputView() {
        return new CarRacingOutputViewImpl();
    }

    private CarRacingInputView carRacingInputView() {
        return new CarRacingInputViewImpl();
    }

    private CarRacingService carRacingService() {
        return new CarRacingService(moveStrategy());
    }

    private MoveStrategy moveStrategy() {
        return new RandomMoveStrategy();
    }
}