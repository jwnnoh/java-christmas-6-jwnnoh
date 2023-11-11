package christmas.controller;

import christmas.domain.Guest;
import christmas.domain.validation.Validator;
import christmas.view.OutputView;
import christmas.view.InputView;

public class ScheduleController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final Validator validator = new Validator();

    public void setDate() {
        outputView.printWelcomeMessage();
        getDateOfVisit();
    }

    private void getDateOfVisit() {
        outputView.printRequestDateOfVisitMessage();
        try {
            String date = inputView.readDateOfVisit();
            validator.validateInputInteger(date);
            Guest guest = new Guest(Integer.parseInt(date));
        } catch (IllegalArgumentException e) {
            outputView.printErrorFromDateMessage();
            getDateOfVisit();
        }
    }
}
