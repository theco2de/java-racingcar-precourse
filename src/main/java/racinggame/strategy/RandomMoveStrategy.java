package racinggame.strategy;

import nextstep.utils.Randoms;

/**
 * 랜덤 전진 전략 구현체
 */
public class RandomMoveStrategy implements MoveStrategy {

    public static final int START_INCLUSIVE = 0;
    public static final int END_INCLUSIVE = 9;
    public static final int MOVE_DELIMITER = 4;

    /**
     * 0 ~ 9사이의 임의의 값을 구해 4를 기준으로 전진 여부를 반환한다.
     * 전진일 경우 참, 거짓을 경우 거짓을 반환한다.
     *
     * @return 전진 여부
     */
    @Override
    public boolean movable() {
        final int count = Randoms.pickNumberInRange(START_INCLUSIVE, END_INCLUSIVE);

        return count >= MOVE_DELIMITER;
    }
}