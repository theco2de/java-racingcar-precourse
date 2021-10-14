package racinggame.view;


import racinggame.domain.dto.CarData;
import racinggame.domain.dto.CarRacingProgress;
import racinggame.domain.dto.CarRacingResultData;

import java.util.Collections;
import java.util.List;

public class CarRacingOutputViewImpl implements CarRacingOutputView {
    private static final StringBuilder sb = new StringBuilder();
    public static final String FINAL_WINNERS_FORMAT = "최종 우승자는 %s 입니다.";
    public static final String SKID_MARK = "-";
    public static final String EMPTY_STR = "";
    public static final String CAR_DATA_FORMAT = "%s : %s";
    public static final String WINNER_DELIMITER = ",";

    @Override
    public void drawRacingProgress(CarRacingResultData resultData) {
        drawRacingProgressList(resultData.getProgressList());
        drawRacingWinners(resultData.getWinners());
    }

    private void drawRacingProgressList(List<CarRacingProgress> progressList) {
        clearStringBuilder();

        for (CarRacingProgress progress : progressList) {
            sb.append(progress.toString(this::toStringCarData))
                    .append(System.lineSeparator());
        }

        System.out.println(sb);
    }

    private void drawRacingWinners(List<String> winnerNames) {
        clearStringBuilder();

        final String winners = String.join(WINNER_DELIMITER, winnerNames);

        sb.append(String.format(FINAL_WINNERS_FORMAT, winners));
        System.out.println(sb);
    }

    private String toStringCarData(CarData carData) {
        final String skidMark = makeDistance(carData.getDistance());

        return String.format(CAR_DATA_FORMAT, carData.getName(), skidMark);
    }

    private String makeDistance(long distance) {
        return String.join(SKID_MARK, Collections.nCopies((int) distance + 1, EMPTY_STR))
                + System.lineSeparator();
    }

    private void clearStringBuilder() {
        sb.replace(0, sb.length(), EMPTY_STR);
    }
}