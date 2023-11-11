package christmas.controller;

import christmas.domain.OrderDetails;
import christmas.domain.service.OrderParser;
import christmas.view.InputView;
import christmas.view.OutputView;

public class OrderController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final OrderParser parser = new OrderParser();
    public void setMenu() {
        outputView.printRequestOrderMessage();
        getOrder();
    }

    private void getOrder() {
        String order = inputView.readOrder();
        try {
             OrderDetails orderDetails = new OrderDetails(parser.splitOrder(order));
        } catch (IllegalArgumentException e) {
            outputView.printErrorFromOrderMessage();
            getOrder();
        }
    }
}
