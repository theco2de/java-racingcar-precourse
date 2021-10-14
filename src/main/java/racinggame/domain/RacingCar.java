package racinggame.domain;


import racinggame.strategy.MoveStrategy;

/**
 * 경주용 자동차 정보
 */
public class RacingCar implements Car {
    public static final long MOVE_DISTANCE = 1;

    private final Name name;
    private final Distance distance;

    public RacingCar(Name name, Distance distance) {
        this.name = name;
        this.distance = distance;
    }

    public static RacingCar of(String name, long distance) {
        return new RacingCar(Name.from(name), Distance.from(distance));
    }

    public static RacingCar from(String name) {
        return of(name, 0);
    }

    @Override
    public Distance getDistance() {
        return distance;
    }

    @Override
    public Name getName() {
        return name;
    }

    /**
     * 자동차 전진 동작으로 인자값 전진 전략의 결과에 따라 참일 경우 이동, 정지를 수행한다.
     *
     * @param strategy 전진 전략
     */
    public void move(MoveStrategy strategy) {
        if (strategy.movable()) {
            distance.changeWith(MOVE_DISTANCE);
        }
    }
}