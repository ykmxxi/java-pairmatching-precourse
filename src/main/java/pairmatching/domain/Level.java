package pairmatching.domain;

import static pairmatching.domain.Mission.LOTTO;
import static pairmatching.domain.Mission.NUMBER_BASEBALL;
import static pairmatching.domain.Mission.PAYMENT;
import static pairmatching.domain.Mission.PERFORMANCE_IMPROVEMENTS;
import static pairmatching.domain.Mission.RACING_CAR;
import static pairmatching.domain.Mission.RELEASE;
import static pairmatching.domain.Mission.SHOPPING_CART;
import static pairmatching.domain.Mission.SUBWAY_MAP;

import java.util.Arrays;
import java.util.List;

public enum Level {

    LEVEL_ONE("레벨1", List.of(RACING_CAR, LOTTO, NUMBER_BASEBALL)),
    LEVEL_TWO("레벨2", List.of(SHOPPING_CART, PAYMENT, SUBWAY_MAP)),
    LEVEL_THREE("레벨3", List.of()),
    LEVEL_FOUR("레벨4", List.of(PERFORMANCE_IMPROVEMENTS, RELEASE)),
    LEVEL_FIVE("레벨5", List.of());

    private final String name;
    private final List<Mission> missionGroup;

    Level(final String name, final List<Mission> missionGroup) {
        this.name = name;
        this.missionGroup = missionGroup;
    }

    public static Mission findMission(final String levelName, final String missionName) {
        Level level = findLevel(levelName);
        validateNoMissionLevel(level);
        return level.missionGroup
                .stream()
                .filter(mission -> mission.hasSameName(missionName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 레벨에 존재하지 않는 미션입니다."));
    }

    public static List<Mission> findMissions(final String levelName) {
        return findLevel(levelName).missionGroup
                .stream()
                .toList();
    }

    private static void validateNoMissionLevel(final Level level) {
        if (hasNoMission(level)) {
            throw new IllegalArgumentException("해당 레벨은 미션이 존재하지 않습니다.");
        }
    }

    private static boolean hasNoMission(final Level level) {
        return level.equals(LEVEL_THREE) || level.equals(LEVEL_FIVE);
    }

    private static Level findLevel(final String levelName) {
        return Arrays.stream(Level.values())
                .filter(level -> level.name.equals(levelName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 레벨은 존재하지 않습니다."));
    }
}
