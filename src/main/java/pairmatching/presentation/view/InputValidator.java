package pairmatching.presentation.view;

import java.util.Objects;

public final class InputValidator {

    public static void validate(final String input) {
        validateNull(input);
        validateBlank(input);
    }

    private static void validateNull(final String input) {
        if (Objects.isNull(input)) {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해 주세요.");
        }
    }

}
