package pairmatching.presentation.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String readFeatureChoice() {
        System.out.print("""
                기능을 선택하세요.
                1. 페어 매칭
                2. 페어 조회
                3. 페어 초기화
                Q. 종료
                """);
        return readString();
    }

    private int readInteger() {
        try {
            return Integer.parseInt(readString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해 주세요.");
        }
    }

    private String readString() {
        String input = Console.readLine()
                .strip();
        InputValidator.validate(input);
        return input;
    }

}
