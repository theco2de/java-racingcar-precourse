package racinggame.domain.dto;

import java.util.Collections;
import java.util.List;

public class CarRacingResultData {
    private final List<CarRacingProgress> progressList;
    private final List<String> winners;

    public CarRacingResultData(List<CarRacingProgress> progressList, List<String> winners) {
        this.progressList = Collections.unmodifiableList(progressList);
        this.winners = Collections.unmodifiableList(winners);
    }

    public List<CarRacingProgress> getProgressList() {
        return progressList;
    }

    public List<String> getWinners() {
        return winners;
    }
}