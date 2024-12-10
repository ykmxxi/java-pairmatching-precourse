package pairmatching.presentation;

import java.util.Arrays;
import java.util.List;

import pairmatching.domain.Pair;
import pairmatching.presentation.file.FileReader;
import pairmatching.presentation.view.InputView;
import pairmatching.presentation.view.OutputView;
import pairmatching.service.PairMatchingService;

public class PairMatchingClient {

    private final InputView inputView;
    private final OutputView outputView;
    private final PairMatchingService pairMatchingService;

    public PairMatchingClient(final InputView inputView, final OutputView outputView,
                              final PairMatchingService pairMatchingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pairMatchingService = pairMatchingService;
        saveCrews();
    }

    private void saveCrews() {
        List<String> backendCrews = FileReader.readBackendCrews();
        List<String> frontendCrews = FileReader.readFrontendCrews();
        pairMatchingService.saveBackendCrews(backendCrews);
        pairMatchingService.saveFrontendCrews(frontendCrews);
    }

    public void run() {
        while (true) {
            String featureChoice = readFeatureChoice();
            if (FeatureCommand.isQuit(featureChoice)) {
                break;
            }
            runFeature(featureChoice);
        }
    }

    private void runFeature(final String featureChoice) {
        if (featureChoice.equals("1")) {
            List<Pair> pairs = runPairMatching();
            outputView.printPairs(pairs);
        }
        if (featureChoice.equals("2")) {
            // 페어 조회
        }
        if (featureChoice.equals("3")) {
            // 페어 초기화
        }
    }

    private String readFeatureChoice() {
        while (true) {
            try {
                String featureChoice = inputView.readFeatureChoice();
                FeatureCommand.validateFeatureCommand(featureChoice);
                return featureChoice;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Pair> runPairMatching() {
        while (true) {
            try {
                List<String> pairMatchingInputs = Arrays.stream(inputView.readPairMatchingInput()
                                .split(","))
                        .map(String::strip)
                        .toList();
                if (existsPair(pairMatchingInputs)) {
                    while (true) {
                        try {
                            String reMatchingInput = inputView.readPairReMatchingInput();
                            if (reMatchingInput.equals("아니요")) {
                                return runPairMatching();
                            }
                            break;
                        } catch (IllegalArgumentException e) {
                            outputView.printErrorMessage(e.getMessage());
                        }
                    }
                }
                return pairMatchingService.matchPair(pairMatchingInputs.get(0), pairMatchingInputs.get(1),
                        pairMatchingInputs.get(2));
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private boolean existsPair(final List<String> pairMatchingInputs) {
        return pairMatchingService.existsPairMatching(pairMatchingInputs.get(0), pairMatchingInputs.get(1),
                pairMatchingInputs.get(2));
    }

}
