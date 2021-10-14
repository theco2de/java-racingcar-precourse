package racinggame.exception;

public class NotFoundValidatorException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "유효한 검증기를 찾을 수 없었습니다.";

    public NotFoundValidatorException() {
        super(DEFAULT_MESSAGE);
    }
}