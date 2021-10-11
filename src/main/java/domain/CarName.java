package domain;

import Constant.Constant;
import Constant.Message;

public class CarName {

    private final String name;

    public CarName(String carName) {
        validateCarName(carName);
        this.name = carName;
    }

    public String getName() {
        return name;
    }

    public void validateCarName(String name) {
        if (name == null || name.trim().isEmpty() || name.trim().length() > Constant.MAX_CAR_NAME)
            throw new IllegalArgumentException(Message.CAR_NAME_ERROR.getValue());
    }
}