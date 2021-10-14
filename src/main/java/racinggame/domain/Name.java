package racinggame.domain;

import racinggame.validation.ErrorResponse;
import racinggame.validation.Validators;
import racinggame.exception.InvalidNameException;

import java.util.Objects;

/**
 * 자동차 이름 정보
 */
public class Name {
    private final String value;

    private Name(String name) {
        this.value = name;
    }

    public static Name from(String name) {
        final ErrorResponse response = Validators.validate(name, Name.class);

        if (!response.isValidated()) {
            throw new InvalidNameException(response.getMessage());
        }

        return new Name(name.trim());
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Name)) {
            return false;
        }
        Name name1 = (Name) o;
        return Objects.equals(value, name1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}