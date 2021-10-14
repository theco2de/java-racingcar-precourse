package racinggame.validation;

import racinggame.exception.InvalidRoundValueException;

public class RoundValidator implements Validator {

    private static final String EMPTY_STR = "";

    @Override
    public String validate(Object source) {
        final int round = (int) source;

        if (round > 0) {
            return EMPTY_STR;
        }

        return String.format(InvalidRoundValueException.DEFAULT_MESSAGE_FORMAT, round);
    }
}