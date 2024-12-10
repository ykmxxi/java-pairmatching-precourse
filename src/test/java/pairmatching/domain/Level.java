package pairmatching.domain;

import static pairmatching.domain.Mission.LOTTO;
import static pairmatching.domain.Mission.NUMBER_BASEBALL;
import static pairmatching.domain.Mission.PAYMENT;
import static pairmatching.domain.Mission.PERFORMANCE_IMPROVEMENTS;
import static pairmatching.domain.Mission.RACING_CAR;
import static pairmatching.domain.Mission.RELEASE;
import static pairmatching.domain.Mission.SHOPPING_CART;
import static pairmatching.domain.Mission.SUBWAY_MAP;

import java.util.List;

public enum Level {

    LEVEL_ONE(List.of(RACING_CAR, LOTTO, NUMBER_BASEBALL)),
    LEVEL_TWO(List.of(SHOPPING_CART, PAYMENT, SUBWAY_MAP)),
    LEVEL_THREE(List.of()),
    LEVEL_FOUR(List.of(PERFORMANCE_IMPROVEMENTS, RELEASE)),
    LEVEL_FIVE(List.of());

    private final List<Mission> missionGroup;

    Level(final List<Mission> missionGroup) {
        this.missionGroup = missionGroup;
    }

}
