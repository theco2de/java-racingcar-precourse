package racinggame.view;

import nextstep.utils.Console;
import racinggame.domain.Cars;
import racinggame.domain.Name;
import racinggame.domain.Round;
import racinggame.validation.ErrorResponse;
import racinggame.validation.Validators;

public class CarRacingInputViewImpl implements CarRacingInputView {
    public static final String ERROR_PREFIX = "[ERROR]";
    public static final String REQUEST_ROUND_MESSAGE = "시도할 회수는 몇회인가요?";
    public static final String REQUEST_CAR_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";

    @Override
    public String requestCarNames() {
        System.out.println(REQUEST_CAR_NAMES_MESSAGE);
        final String names = Console.readLine();
        final String message = nameValidateResult(names);

        if (!message.isEmpty()) {
            printErrorMessage(message);
            return requestCarNames();
        }

        return names;
    }

    private String nameValidateResult(String names) {
        final StringBuilder sb = new StringBuilder();

        for (String splitName : names.split(Cars.DELIMITER)) {
            sb.append(Validators.validate(splitName, Name.class).getMessage());
        }
        return sb.toString();
    }

    @Override
    public int requestRound() {
        try {
            System.out.println(REQUEST_ROUND_MESSAGE);
            final String roundStr = Console.readLine();
            final int round = Integer.parseInt(roundStr);

            final ErrorResponse response = Validators.validate(round, Round.class);

            if (!response.isValidated()) {
                printErrorMessage(response.getMessage());
                return requestRound();
            }
            return round;

        } catch (NumberFormatException e) {
            printErrorMessage(e.getMessage());
            return requestRound();
        }
    }

    private void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}