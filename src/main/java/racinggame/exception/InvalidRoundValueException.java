package racinggame.exception;

public class InvalidRoundValueException extends RuntimeException {
    public static final String DEFAULT_MESSAGE_FORMAT = "유효하지 않은 게임 진행 횟수입니다. 입력: [%d]";

    public InvalidRoundValueException(int round) {
        super(String.format(DEFAULT_MESSAGE_FORMAT, round));
    }

    public InvalidRoundValueException(String message) {
        super(message);
    }
}