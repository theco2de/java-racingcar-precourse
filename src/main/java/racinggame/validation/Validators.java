package racinggame.validation;

import racinggame.exception.NotFoundValidatorException;
import racinggame.exception.ParameterNullPointException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 유효성 검증 유틸 클래스
 */
public class Validators {
    private static final Map<Class<?>, Validator> store = new HashMap<>();

    private Validators() {
    }

    /**
     * 검증기를 새로 등록한다. 검증을 원하는 타입을 KEY로 사용한다.
     *
     * @param type      검증 대상 타입
     * @param validator 검증기
     */
    public static void addValidator(Class<?> type, Validator validator) {
        store.put(type, validator);
    }

    /**
     * 검증을 수행한 뒤 결과를 반환한다. 각각의 타입에 맞는 검증을 수행하기 전 공통적으로 해당 값의 Null check와 해당 타입을 지원하는
     * 검증기가 있는지 확인한다.
     *
     * @param value 검증 대상 값
     * @param type  검증 지원 타입
     * @return 예외 응답 객체
     */
    public static ErrorResponse validate(Object value, Class<?> type) {
        if (Objects.isNull(value)) {
            return ErrorResponse.from(ParameterNullPointException.DEFAULT_MESSAGE);
        }
        if (!store.containsKey(type)) {
            return ErrorResponse.from(NotFoundValidatorException.DEFAULT_MESSAGE);
        }

        final Validator validator = store.get(type);

        return ErrorResponse.from(validator.validate(value));
    }


}