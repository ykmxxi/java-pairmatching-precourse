package pairmatching.repository;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;

public class CrewRepository {

    private static final Map<Course, List<Crew>> CREWS = new EnumMap<>(Course.class);


    public void save(final Course course, final List<Crew> crews) {
        CREWS.put(course, crews);
    }

}
