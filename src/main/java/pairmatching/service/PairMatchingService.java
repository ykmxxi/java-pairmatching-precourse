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

    public boolean existsPairMatching(final String courseName, final String levelName, final String missionName) {
        Course course = Course.find(courseName);
        Mission mission = Level.findMission(levelName, missionName);
        return pairMatchingRepository.containsPair(course, mission);
    }

    public List<Pair> matchPair(final String courseName, final String levelName, final String missionName) {
        Course course = Course.find(courseName);
        Mission mission = Level.findMission(levelName, missionName);
        List<Crew> crews = crewRepository.find(course);
        List<Mission> missions = Level.findMissions(levelName);
        List<Pair> pairs = match(course, missions, crews);
        pairMatchingRepository.save(course, mission, pairs);
        return pairs;
    }

    public List<Pair> findPair(final String courseName, final String levelName, final String missionName) {
        Course course = Course.find(courseName);
        Mission mission = Level.findMission(levelName, missionName);
        return pairMatchingRepository.findPairs(course, mission);
    }

    public void clearPairHistory() {
        pairMatchingRepository.clear();
    }

    private List<Pair> match(final Course course, final List<Mission> missions, final List<Crew> crews) {
        List<Pair> pairs = getPairs(Randoms.shuffle(crews.stream().map(Crew::getName).toList()), course);
        int matchingCount = 1;
        while (hasPairMatchingHistory(course, pairs, missions)) {
            validateOverMatchingCount(matchingCount);
            pairs = getPairs(Randoms.shuffle(crews.stream().map(Crew::getName).toList()), course);
            matchingCount++;
        }
        return pairs;
    }

    private void validateOverMatchingCount(final int matchingCount) {
        if (matchingCount > 3) {
            throw new IllegalStateException("페어 매칭이 불가능합니다.");
        }
    }

    private boolean hasPairMatchingHistory(final Course course, final List<Pair> pairs, final List<Mission> missions) {
        for (Mission mission : missions) {
            List<Pair> otherPairs = pairMatchingRepository.findPairs(course, mission);
            if (isNeedReMatching(pairs, otherPairs)) {
                return true;
            }
        }
        return false;
    }

    private List<Pair> getPairs(final List<String> shuffledCrewNames, final Course course) {
        List<Pair> pairs = new ArrayList<>();
        int countOfPair = shuffledCrewNames.size() / 2;
        for (int pairIndex = 0; pairIndex < countOfPair; pairIndex++) {
            List<Crew> pairCrews = getPairCrews(shuffledCrewNames, pairIndex, countOfPair, course);
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

    private List<Crew> getPairCrews(final List<String> shuffledCrewNames, final int index, final int countOfPair,
                                    final Course course
    ) {
        int startIndex = index * 2;
        List<Crew> pairCrews = new ArrayList<>();
        pairCrews.add(new Crew(shuffledCrewNames.get(startIndex), course));
        pairCrews.add(new Crew(shuffledCrewNames.get(startIndex + 1), course));
        if (isThreePair(shuffledCrewNames.size(), index, countOfPair)) {
            pairCrews.add(new Crew(shuffledCrewNames.get(startIndex + 2), course));
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
