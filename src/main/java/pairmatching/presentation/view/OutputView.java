package pairmatching.presentation.view;

public class OutputView {

    public void printErrorMessage(final String message) {
        System.out.println(String.join(" ", "[ERROR]", message));
    }
}
