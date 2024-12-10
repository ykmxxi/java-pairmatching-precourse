package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static pairmatching.domain.Course.BACKEND;
import static pairmatching.domain.Course.FRONTEND;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CourseTest {

    @DisplayName("과정 이름으로 해당 과정을 조회한다.")
    @MethodSource("provideCourseNameAndExpectedCourse")
    @ParameterizedTest
    void 과정_이름으로_과정_조회(String courseName, Course expectedCourse) {
        assertThat(Course.find(courseName)).isEqualTo(expectedCourse);
    }

    private static Stream<Arguments> provideCourseNameAndExpectedCourse() {
        return Stream.of(
                Arguments.of("백엔드", BACKEND),
                Arguments.of("프론트엔드", FRONTEND)
        );
    }

}
