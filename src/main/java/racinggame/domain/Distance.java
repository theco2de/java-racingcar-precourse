package racinggame.domain;


import racinggame.validation.ErrorResponse;
import racinggame.validation.Validators;
import racinggame.exception.InvalidDistanceException;

import java.util.Objects;

/**
 * 거리 정보
 */
public class Distance {
    private long value;

    private Distance(long value) {
        this.value = value;
    }

    public static Distance from(long distance) {
        final ErrorResponse response = Validators.validate(distance, Distance.class);

        if (!response.isValidated()) {
            throw new InvalidDistanceException(response.getMessage());
        }

        return new Distance(distance);
    }

    public static Distance of(long distance, Distance source) {
        return from(distance + source.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Distance)) {
            return false;
        }
        Distance distance = (Distance) o;
        return value == distance.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + "";
    }

    protected void changeWith(long distance) {
        this.value += distance;
    }

    public long getValue() {
        return value;
    }
}