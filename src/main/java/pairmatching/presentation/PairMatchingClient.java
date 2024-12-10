package pairmatching.presentation;

import java.util.List;

import pairmatching.presentation.file.FileReader;
import pairmatching.service.PairMatchingService;

public class PairMatchingClient {

    private final PairMatchingService pairMatchingService;

    public PairMatchingClient(final PairMatchingService pairMatchingService) {
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
    }

}
