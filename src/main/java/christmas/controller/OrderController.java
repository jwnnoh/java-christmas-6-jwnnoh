package christmas.controller;

import christmas.domain.OrderDetails;
import christmas.domain.service.OrderParser;
import christmas.domain.validation.Validator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class OrderController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final OrderParser parser = new OrderParser();
    private final Validator validator = new Validator();
    public void setMenu() {
        outputView.printRequestOrderMessage();
        getOrder();
    }

    private void getOrder() {
        String order = inputView.readOrder();
        try {
            String[] splitOrder = parser.splitOrder(order);
            validator.validateDuplicates(splitOrder);
            OrderDetails orderDetails = new OrderDetails(splitOrder);
        } catch (IllegalArgumentException e) {
            outputView.printErrorFromOrderMessage();
            getOrder();
        }
    }
}
