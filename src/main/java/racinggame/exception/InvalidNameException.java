package racinggame.exception;

public class InvalidNameException extends RuntimeException {
    public static final String DEFAULT_MESSAGE_FORMAT = "이름은 5글자 이내여야 합니다. 입력 값:[%s]";

    public InvalidNameException(String message) {
        super(message);
    }
}