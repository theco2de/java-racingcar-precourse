package racinggame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 자동차 객체들을 관리하는 일급 컬렉션
 */
public class Cars {
    public static final String DELIMITER = ",";
    private final List<Car> store = new ArrayList<>();

    private Cars() {
    }

    public static Cars newInstance() {
        return new Cars();
    }

    public static Cars from(List<Car> carList) {
        final Cars cars = newInstance();
        cars.addAll(carList);

        return cars;
    }

    public static Cars from(String carNames) {
        final Cars cars = newInstance();

        for (String name : carNames.split(DELIMITER)) {
            cars.add(RacingCar.from(name));
        }

        return cars;
    }

    public void addAll(List<Car> carList) {
        store.addAll(carList);
    }

    public void add(Car car) {
        store.add(car);
    }

    /**
     * 모든 자동차에게 인자로 받은 동작을 수행시키도록 한다.
     *
     * @param consumer 자동차가 수행할 동작.
     */
    public void notifyAll(Consumer<Car> consumer) {
        for (Car car : store) {
            consumer.accept(car);
        }
    }

    /**
     * 자동차목록을 순회하며 인자로 전달받은 동작을 수행하여 결과를 리스트화 하여 반환한다.
     *
     * @param function 자동차 객체를 인자로 동작을 수행하여 결과를 반환하는 함수
     * @param <T>      반환할 리스트의 요소 타입
     * @return 변환된 요소들의 목록
     */
    public <T> List<T> map(Function<Car, T> function) {
        List<T> list = new ArrayList<>();

        for (Car car : store) {
            list.add(function.apply(car));
        }

        return list;
    }

    /**
     * 목록에 인자로 전달받은 자동차의 존재 여부를 반환한다.
     *
     * @param car 조회할 자동차
     * @return 존재 여부
     */
    public boolean contains(Car car) {
        return store.contains(car);
    }

    /**
     * 이동 거리를 기준으로 우승자 목록 구해 이름 목록을 반환 한다.
     *
     * @return 우승자 이름 목록
     */
    public List<String> getWinners() {
        long maxDistance = 0;
        Map<Long, List<String>> winnersMap = new HashMap<>();

        for (Car car : store) {
            maxDistance = Math.max(car.getDistance().getValue(), maxDistance);
            winnersMap.merge(car.getDistance().getValue(),
                    new ArrayList<>(Collections.singletonList(car.getName().toString())),
                    this::mergeList);
        }
        return winnersMap.get(maxDistance);
    }

    private List<String> mergeList(List<String> list, List<String> putList) {
        list.addAll(putList);
        return list;
    }
}