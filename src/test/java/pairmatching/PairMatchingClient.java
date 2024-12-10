package pairmatching;

import java.util.List;

import pairmatching.presentation.file.FileReader;

public class PairMatchingClient {

    public void run() {
        List<String> backendCrews = FileReader.readBackendCrews();
        List<String> frontendCrews = FileReader.readFrontendCrews();
    }

}
