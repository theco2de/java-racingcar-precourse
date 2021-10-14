package racinggame.exception;

public class ParameterNullPointException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "값이 존재하지 않습니다.";

    public ParameterNullPointException() {
        super(DEFAULT_MESSAGE);
    }
}
