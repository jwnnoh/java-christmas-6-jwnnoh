package christmas.controller;

import christmas.domain.OrderDetails;
import christmas.domain.service.OrderParser;
import christmas.domain.validation.Validator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

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
            Map<String, Integer> parsedMenu = parser.splitMenu(parser.splitOrder(order));
            OrderDetails orderDetails = new OrderDetails(parsedMenu);
        } catch (IllegalArgumentException e) {
            outputView.printErrorFromOrderMessage();
            getOrder();
        }
    }
}
