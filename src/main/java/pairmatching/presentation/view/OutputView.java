package pairmatching.presentation.view;

import java.util.List;

import pairmatching.domain.Pair;

public class OutputView {

    public void printErrorMessage(final String message) {
        System.out.println(String.join(" ", "[ERROR]", message));
    }

    public void printPairs(final List<Pair> pairs) {
        System.out.println();
        System.out.println("페어 매칭 결과입니다.");
        for (Pair pair : pairs) {
            System.out.println(String.join(" : ", pair.getNames()));
        }
        System.out.println();
    }

    public void printClearMessage() {
        System.out.println();
        System.out.println("초기화 되었습니다.");
        System.out.println();
    }

}
