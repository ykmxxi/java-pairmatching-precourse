package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pairmatching.domain.Mission.NONE;
import static pairmatching.domain.Mission.PERFORMANCE_IMPROVEMENTS;
import static pairmatching.domain.Mission.RACING_CAR;
import static pairmatching.domain.Mission.SHOPPING_CART;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LevelTest {

    @DisplayName("해당 레벨에 존재하는 미션 조회를 성공한다.")
    @MethodSource("provideLevelNameAndMissionName")
    @ParameterizedTest
    void 미션_조회_성공(String levelName, String missionName, Mission expected) {
        Mission mission = Level.findMission(levelName, missionName);

        assertThat(mission).isEqualTo(expected);
    }

    @DisplayName("해당 레벨이 존재하지 않으면 예외를 던진다.")
    @Test
    void 미션_조회_실패_존재하지_않는_레벨() {
        assertThatThrownBy(() -> Level.findMission("레벨6", "취업"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 레벨은 존재하지 않습니다.");
    }

    @DisplayName("해당 레벨에 존재하지 않는 미션이면 예외를 던진다.")
    @Test
    void 미션_조회_실패_존재하지_않는_미션() {
        assertThatThrownBy(() -> Level.findMission("레벨1", "장바구니"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 레벨에 존재하지 않는 미션입니다.");
    }

    private static Stream<Arguments> provideLevelNameAndMissionName() {
        return Stream.of(
                Arguments.of("레벨1", "자동차경주", RACING_CAR),
                Arguments.of("레벨2", "장바구니", SHOPPING_CART),
                Arguments.of("레벨4", "성능개선", PERFORMANCE_IMPROVEMENTS)
        );
    }

}
