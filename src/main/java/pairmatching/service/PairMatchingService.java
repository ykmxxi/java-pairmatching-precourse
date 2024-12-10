package pairmatching.service;

import static pairmatching.domain.Course.BACKEND;
import static pairmatching.domain.Course.FRONTEND;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.PairMatchingRepository;

public class PairMatchingService {

    private final CrewRepository crewRepository;
    private final PairMatchingRepository pairMatchingRepository;

    public PairMatchingService(final CrewRepository crewRepository,
                               final PairMatchingRepository pairMatchingRepository
    ) {
        this.crewRepository = crewRepository;
        this.pairMatchingRepository = pairMatchingRepository;
    }

    public void saveBackendCrews(final List<String> backendCrewNames) {
        crewRepository.save(BACKEND, mapToCrews(backendCrewNames, BACKEND));
    }

    public void saveFrontendCrews(final List<String> frontendCrewNames) {
        crewRepository.save(FRONTEND, mapToCrews(frontendCrewNames, FRONTEND));
    }

    public List<Pair> matchPair(final String courseName, final String levelName, final String missionName) {
        Course course = Course.find(courseName);
        Mission mission = Level.findMission(levelName, missionName);
        List<Crew> crews = crewRepository.find(course);
        List<Mission> missions = Level.findMissions(levelName);
        List<Pair> pairs = match(missions, crews);
        pairMatchingRepository.save(mission, pairs);
        return pairs;
    }

    public List<Pair> findPair(final String courseName, final String levelName, final String missionName) {
        Course course = Course.find(courseName);
        Mission mission = Level.findMission(levelName, missionName);
        return pairMatchingRepository.findPairs(mission);
    }

    public void clearPairHistory() {
        pairMatchingRepository.clear();
    }

    private List<Pair> match(final List<Mission> missions, final List<Crew> crews) {
        List<Pair> pairs = getPairs(Randoms.shuffle(crews));
        int matchingCount = 1;
        while (hasPairMatchingHistory(pairs, missions)) {
            validateOverMatchingCount(matchingCount);
            pairs = getPairs(Randoms.shuffle(crews));
            matchingCount++;
        }
        return pairs;
    }

    private void validateOverMatchingCount(final int matchingCount) {
        if (matchingCount > 3) {
            throw new IllegalStateException("페어 매칭이 불가능합니다.");
        }
    }

    private boolean hasPairMatchingHistory(final List<Pair> pairs, final List<Mission> missions) {
        for (Mission mission : missions) {
            List<Pair> otherPairs = pairMatchingRepository.findPairs(mission);
            if (isNeedReMatching(pairs, otherPairs)) {
                return true;
            }
        }
        return false;
    }

    private List<Pair> getPairs(final List<Crew> shuffledCrews) {
        List<Pair> pairs = new ArrayList<>();
        int countOfPair = shuffledCrews.size() / 2;
        for (int pairIndex = 0; pairIndex < countOfPair; pairIndex++) {
            List<Crew> pairCrews = getPairCrews(shuffledCrews, pairIndex, countOfPair);
            pairs.add(new Pair(pairCrews));
        }
        return pairs;
    }

    private boolean isNeedReMatching(final List<Pair> pairs, final List<Pair> otherPairs) {
        for (Pair pair : pairs) {
            if (pair.isThree()) {
                List<Pair> subPairs = pair.getSubPairs();
                for (Pair subPair : subPairs) {
                    return otherPairs.contains(subPair);
                }
            }
            return otherPairs.contains(pair);
        }
        return false;
    }

    private List<Crew> getPairCrews(final List<Crew> shuffledCrews, final int index, final int countOfPair) {
        int startIndex = index * 2;
        List<Crew> pairCrews = new ArrayList<>();
        pairCrews.add(shuffledCrews.get(startIndex));
        pairCrews.add(shuffledCrews.get(startIndex + 1));
        if (isThreePair(shuffledCrews.size(), index, countOfPair)) {
            pairCrews.add(shuffledCrews.get(startIndex + 2));
        }
        return pairCrews;
    }

    private boolean isThreePair(final int countOfCrew, final int index, final int countOfPair) {
        return (index == countOfPair - 1) && (countOfCrew % 2 == 1);
    }

    private List<Crew> mapToCrews(final List<String> crewNames, final Course course) {
        return crewNames.stream()
                .map(name -> new Crew(name, course))
                .toList();
    }

}
