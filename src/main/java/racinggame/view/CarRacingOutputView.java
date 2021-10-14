package racinggame.view;

import racinggame.domain.dto.CarRacingResultData;

public interface CarRacingOutputView {
    void drawRacingProgress(CarRacingResultData resultData);
}