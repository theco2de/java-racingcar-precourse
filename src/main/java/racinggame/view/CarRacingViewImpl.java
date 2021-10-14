package racinggame.view;

import racinggame.domain.dto.CarRacingResultData;

public class CarRacingViewImpl implements CarRacingView {
    private final CarRacingInputView carRacingInputView;
    private final CarRacingOutputView carRacingOutputView;

    public CarRacingViewImpl(CarRacingInputView carRacingInputView, CarRacingOutputView carRacingOutputView) {
        this.carRacingInputView = carRacingInputView;
        this.carRacingOutputView = carRacingOutputView;
    }


    @Override
    public String requestCarNames() {
        return carRacingInputView.requestCarNames();
    }

    @Override
    public int requestRound() {
        return carRacingInputView.requestRound();
    }

    @Override
    public void drawRacingProgress(CarRacingResultData resultData) {
        carRacingOutputView.drawRacingProgress(resultData);
    }
}