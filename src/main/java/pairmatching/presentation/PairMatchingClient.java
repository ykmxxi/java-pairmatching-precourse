package pairmatching.presentation;

import java.util.List;

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
            if (featureChoice.equals("1")) {
                // 페어 매칭
            }
            if (featureChoice.equals("2")) {
                // 페어 조회
            }
            if (featureChoice.equals("3")) {
                // 페어 초기화
                pairMatchingService.clearPairHistory();
            }
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

}
