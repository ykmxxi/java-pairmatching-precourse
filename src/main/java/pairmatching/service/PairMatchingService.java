package pairmatching.service;

import static pairmatching.domain.Course.BACKEND;
import static pairmatching.domain.Course.FRONTEND;

import java.util.List;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.repository.CrewRepository;

public class PairMatchingService {

    private final CrewRepository crewRepository;

    public PairMatchingService(final CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public void saveBackendCrews(final List<String> backendCrewNames) {
        crewRepository.save(BACKEND, mapToCrews(backendCrewNames, BACKEND));
    }

    public void saveFrontendCrews(final List<String> frontendCrewNames) {
        crewRepository.save(FRONTEND, mapToCrews(frontendCrewNames, FRONTEND));
    }

    private List<Crew> mapToCrews(final List<String> crewNames, final Course course) {
        return crewNames.stream()
                .map(name -> new Crew(name, course))
                .toList();
    }

}
