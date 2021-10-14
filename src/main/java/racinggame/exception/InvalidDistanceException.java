package racinggame.exception;

public class InvalidDistanceException extends RuntimeException {
    public static final String DEFAULT_MESSAGE_FORMAT = "유효하지 않은 숫자입니다. 입력 값:[%d]";

    public InvalidDistanceException(String message) {
        super(message);
    }
}