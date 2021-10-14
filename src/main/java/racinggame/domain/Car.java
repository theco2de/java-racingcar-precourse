package racinggame.domain;

import racinggame.strategy.DistanceSupplier;
import racinggame.strategy.MoveStrategy;
import racinggame.strategy.NameSupplier;

/**
 * 자동차의 동작을 정의한다.
 */
public interface Car extends NameSupplier<Name>, DistanceSupplier<Distance> {

    void move(MoveStrategy strategy);
}