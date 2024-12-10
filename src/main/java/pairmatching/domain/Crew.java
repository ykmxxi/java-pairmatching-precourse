package pairmatching.domain;

import java.util.Objects;

public class Crew {

    private final String name;
    private final Course course;

    public Crew(final String name, final Course course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Crew crew)) {
            return false;
        }
        return Objects.equals(name, crew.name) && course == crew.course;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, course);
    }

}
