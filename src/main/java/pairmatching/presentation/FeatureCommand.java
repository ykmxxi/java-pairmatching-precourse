package pairmatching.presentation;

import java.util.Arrays;
import java.util.List;

public enum FeatureCommand {

    PAIR_MATCHING("1"),
    PAIR_INQUIRY("2"),
    PAIR_CLEAR("3"),
    QUIT("Q");

    private final String command;

    FeatureCommand(final String command) {
        this.command = command;
    }

    public static void validateFeatureCommand(final String commandInput) {
        List<String> featureCommands = Arrays.stream(values())
                .map(feature -> feature.command)
                .toList();
        if (!featureCommands.contains(commandInput)) {
            throw new IllegalArgumentException("잘못된 기능 선택 입력입니다.");
        }
    }

    public static boolean isQuit(final String featureChoice) {
        return QUIT.command.equals(featureChoice);
    }
}
