package christmas.view;

import christmas.view.constants.ConstantMessage;

public class OutputView {

    public void printWelcomeMessage() {
        System.out.println(ConstantMessage.WELCOME.getMessage());
    }

    public void printRequestDateOfVisitMessage() {
        System.out.println(ConstantMessage.REQUEST_DATE_OF_VISIT.getMessage());
    }
}
