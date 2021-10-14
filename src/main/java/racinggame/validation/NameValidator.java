package racinggame.validation;

import racinggame.exception.InvalidNameException;

import java.util.Objects;

/**
 * 이름 검증기
 */
public class NameValidator implements Validator {
    public static final String EMPTY_STR = "";

    /**
     * 이름 문자열이 5글자 이내이고 공백이거나 NULL인지 검사하여 결과를 반환한다.
     *
     * @param source 이름 정보
     * @return 예외 내용
     */
    @Override
    public String validate(Object source) {
        final String name = String.valueOf(source);

        if (Objects.nonNull(name)
                && name.trim().length() < 6
                && !name.trim().isEmpty()) {
            return EMPTY_STR;
        }

        return String.format(InvalidNameException.DEFAULT_MESSAGE_FORMAT, name);
    }
}