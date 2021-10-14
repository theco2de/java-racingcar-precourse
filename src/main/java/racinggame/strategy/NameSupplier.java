package racinggame.strategy;

import racinggame.domain.Name;

/**
 * 이름 정보를 제공한다.
 */
@FunctionalInterface
public interface NameSupplier<T extends Name> {
    T getName();
}