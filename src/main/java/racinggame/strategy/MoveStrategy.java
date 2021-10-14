package racinggame.strategy;

/**
 * 전진 전략을 정의한다.
 */
@FunctionalInterface
public interface MoveStrategy {
    /**
     * 전진을 해도 되는 경우 참을 반환하고, 아닐 경우 거짓을 반환한다.
     *
     * @return 전진 여부
     */
    boolean movable();
}
