package racinggame.domain;


import racinggame.validation.ErrorResponse;
import racinggame.validation.Validators;
import racinggame.exception.InvalidRoundValueException;
import racinggame.exception.RoundOverProcessException;

/**
 * 게임 진행 횟수 정보
 */
public class Round {
    private int progress;
    private final int end;

    public Round(int round) {
        final ErrorResponse response = Validators.validate(round, Round.class);

        if (!response.isValidated()) {
            throw new InvalidRoundValueException(response.getMessage());
        }

        this.progress = 0;
        this.end = round;
    }

    public boolean hasNext() {
        return progress < end;
    }

    public int next() {
        if (progress >= end) {
            throw new RoundOverProcessException();
        }

        return ++progress;
    }
}