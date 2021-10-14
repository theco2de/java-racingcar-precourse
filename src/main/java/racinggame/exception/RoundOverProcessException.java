package racinggame.exception;

public class RoundOverProcessException extends RuntimeException{
    public static final String DEFAULT_MESSAGE = "유효한 라운드 횟수를 벗어났습니다.";

    public RoundOverProcessException() {
        super(DEFAULT_MESSAGE);
    }
}