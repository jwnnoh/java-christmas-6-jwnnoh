package christmas.view;

import christmas.view.constants.ConstantMessage;

public class OutputView {

    public void printErrorFromDateMessage() {
        System.out.println(ConstantMessage.ERROR_PREFIX.getMessage() +
                ConstantMessage.ERROR_DATE.getMessage());
    }

    public void printErrorFromOrderMessage() {
        System.out.println(ConstantMessage.ERROR_PREFIX.getMessage() +
                ConstantMessage.ERROR_ORDER.getMessage());
    }

    public void printWelcomeMessage() {
        System.out.println(ConstantMessage.WELCOME.getMessage());
    }

    public void printRequestDateOfVisitMessage() {
        System.out.println(ConstantMessage.REQUEST_DATE_OF_VISIT.getMessage());
    }

    public void printRequestOrderMessage() {
        System.out.println(ConstantMessage.REQUEST_ORDER.getMessage());
    }
}
