package racinggame.exception;

public class OutOfIndexException extends RuntimeException {
    public static final String DEFAULT_MESSAGE_FORMAT = "유효하지 않은 인덱스입니다. 입력: [%d]";

    public OutOfIndexException(int index) {
        super(String.format(DEFAULT_MESSAGE_FORMAT, index));
    }
}