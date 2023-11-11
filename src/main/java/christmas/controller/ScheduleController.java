package christmas.controller;

import christmas.view.OutputView;

public class ScheduleController {
    private final OutputView outputView = new OutputView();

    public void setDate() {
        outputView.printWelcomeMessage();
        getDateOfVisit();
    }

    private void getDateOfVisit() {
        outputView.printRequestDateOfVisitMessage();
    }
}
