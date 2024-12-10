package pairmatching.repository;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import pairmatching.domain.Mission;
import pairmatching.domain.Pair;

public class PairMatchingRepository {

    private static final Map<Mission, List<Pair>> MATCHING_HISTORIES = new EnumMap<>(Mission.class);

    public void save(final Mission mission, final List<Pair> pairs) {
        MATCHING_HISTORIES.put(mission, pairs);
    }

    public List<Pair> findPairs(final Mission mission) {
        return MATCHING_HISTORIES.getOrDefault(mission, List.of());
    }

    public void clear() {
        MATCHING_HISTORIES.clear();
    }

}
