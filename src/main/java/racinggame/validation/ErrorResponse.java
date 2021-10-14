package racinggame.validation;

import java.util.Objects;

/**
 * 예외 응답 정보
 */
public class ErrorResponse {
    private final boolean validated;
    private final String message;


    public ErrorResponse(boolean validated, String message) {
        this.validated = validated;
        this.message = message;
    }

    public static ErrorResponse from(String message) {
        if (Objects.nonNull(message) && !message.isEmpty()) {
            return new ErrorResponse(false, message);
        }
        return new ErrorResponse(true, message);
    }

    /**
     * 유효 여부를 반환한다.
     *
     * @return 유효 여부
     */
    public boolean isValidated() {
        return validated;
    }


    public String getMessage() {
        return message;
    }
}