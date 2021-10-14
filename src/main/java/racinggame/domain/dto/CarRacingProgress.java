package racinggame.domain.dto;

import racinggame.exception.OutOfIndexException;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class CarRacingProgress {
    private final int round;
    private final List<CarData> store;

    public CarRacingProgress(int round, List<CarData> store) {
        this.round = round;
        this.store = Collections.unmodifiableList(store);
    }

    public CarData get(int index) {
        if (index < 0 || index >= store.size()) {
            throw new OutOfIndexException(index);
        }

        return store.get(index);
    }

    public String toString(Function<CarData, String> function) {
        String progressStr = "";
        for (CarData carData : store) {
            progressStr = progressStr.concat(function.apply(carData));
        }

        return progressStr;
    }

    public int size() {
        return store.size();
    }

    public int getRound() {
        return round;
    }
}