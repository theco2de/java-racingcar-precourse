package racinggame.validation;

import racinggame.exception.InvalidDistanceException;

/**
 * 거리 유효성 검증기
 */
public class DistanceValidator implements Validator {

    public static final String EMPTY_STR = "";
    public static final int VALID_DISTANCE_DELIMITER = 0;

    /**
     * 거리가 0보다 크거나 같을 경우 공백을 반환하고 그외는 예외ㅓ 메세지를 반환한다.
     *
     * @param source 거리 정보
     * @return 응답 결과 메세지
     */
    @Override
    public String validate(Object source) {
        long distance = (long) source;

        if (distance >= VALID_DISTANCE_DELIMITER) {
            return EMPTY_STR;
        }

        return String.format(InvalidDistanceException.DEFAULT_MESSAGE_FORMAT, distance);
    }
}
