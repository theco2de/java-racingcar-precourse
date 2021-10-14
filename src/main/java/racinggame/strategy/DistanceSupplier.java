package racinggame.strategy;

import racinggame.domain.Distance;

/**
 * 거리 정보를 반환한다.
 */
@FunctionalInterface
public interface DistanceSupplier<T extends Distance> {
    T getDistance();
}