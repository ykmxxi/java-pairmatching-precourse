package pairmatching.domain;

import java.util.Arrays;

public enum Course {

    BACKEND("백엔드"), FRONTEND("프론트엔드");

    private final String name;

    Course(final String name) {
        this.name = name;
    }

    public static Course find(final String courseName) {
        return Arrays.stream(Course.values())
                .filter(course -> course.name.equals(courseName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과정입니다. 다시 입력해주세요."));
    }

}
