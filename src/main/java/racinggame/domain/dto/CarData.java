package racinggame.domain.dto;

import racinggame.domain.Car;

public class CarData {
    private final String name;
    private final long distance;

    public CarData(String name, long distance) {
        this.name = name;
        this.distance = distance;
    }

    public static CarData from(Car car) {
        return new CarData(
                car.getName().toString(),
                car.getDistance().getValue());
    }

    public String getName() {
        return name;
    }

    public long getDistance() {
        return distance;
    }
}