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

    public String readPairMatchingInput() {
        System.out.println();
        System.out.print("""
                #############################################
                과정: 백엔드 | 프론트엔드
                미션:
                  - 레벨1: 자동차경주 | 로또 | 숫자야구게임
                  - 레벨2: 장바구니 | 결제 | 지하철노선도
                  - 레벨3:
                  - 레벨4: 성능개선 | 배포
                  - 레벨5:
                #############################################
                과정, 레벨, 미션을 선택하세요.
                ex) 백엔드, 레벨1, 자동차경주
                """);
        return readString();
    }

    public String readPairReMatchingInput() {
        System.out.print("""
                매칭 정보가 있습니다. 다시 매칭하시겠습니까?
                네 | 아니오
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
