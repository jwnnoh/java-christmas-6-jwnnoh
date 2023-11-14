package christmas.controller;

import christmas.domain.Guest;
import christmas.domain.validation.Validator;
import christmas.view.OutputView;
import christmas.view.InputView;

public class ScheduleController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final Validator validator = new Validator();

    private Guest guest;

    public void setDate() {
        outputView.printWelcomeMessage();
        outputView.printRequestDateOfVisitMessage();
        getDateOfVisit();
    }

    private void getDateOfVisit() {
        String date = inputView.readDateOfVisit();
        try {
            guest = new Guest(validator.validateInputInteger(date));
        } catch (IllegalArgumentException e) {
            outputView.printErrorFromDateMessage();
            getDateOfVisit();
        }
    }

    public Guest getGuest() {
        return guest;
    }
}
