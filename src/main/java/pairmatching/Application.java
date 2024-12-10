package pairmatching;

import pairmatching.presentation.PairMatchingClient;
import pairmatching.presentation.view.InputView;
import pairmatching.presentation.view.OutputView;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.PairMatchingRepository;
import pairmatching.service.PairMatchingService;

public class Application {

    public static void main(String[] args) {
        PairMatchingService pairMatchingService = new PairMatchingService(
                new CrewRepository(), new PairMatchingRepository()
        );
        PairMatchingClient pairMatchingClient = new PairMatchingClient(
                new InputView(), new OutputView(), pairMatchingService
        );
        pairMatchingClient.run();
    }

}
