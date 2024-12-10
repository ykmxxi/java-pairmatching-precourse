package pairmatching.repository;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import pairmatching.domain.Course;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;

public class PairMatchingRepository {

    private static final Map<Mission, List<Pair>> BACKEND_MATCHING_HISTORIES = new EnumMap<>(Mission.class);
    private static final Map<Mission, List<Pair>> FRONTEND_MATCHING_HISTORIES = new EnumMap<>(Mission.class);

    public void save(final Course course, final Mission mission, final List<Pair> pairs) {
        if (course.isBackend()) {
            BACKEND_MATCHING_HISTORIES.put(mission, pairs);
        }
        FRONTEND_MATCHING_HISTORIES.put(mission, pairs);
    }

    public List<Pair> findPairs(final Course course, final Mission mission) {
        if (course.isBackend()) {
            return BACKEND_MATCHING_HISTORIES.getOrDefault(mission, List.of());
        }
        return FRONTEND_MATCHING_HISTORIES.getOrDefault(mission, List.of());
    }

    public boolean containsPair(final Course course, final Mission mission) {
        if (course.isBackend()) {
            return BACKEND_MATCHING_HISTORIES.containsKey(mission);
        }
        return FRONTEND_MATCHING_HISTORIES.containsKey(mission);
    }

    public void clear() {
        BACKEND_MATCHING_HISTORIES.clear();
        FRONTEND_MATCHING_HISTORIES.clear();
    }

}
