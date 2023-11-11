package christmas.controller;

import christmas.domain.OrderDetails;
import christmas.domain.service.OrderParser;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class OrderController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final OrderParser parser = new OrderParser();
    private OrderDetails orderDetails;

    public void setMenu() {
        outputView.printRequestOrderMessage();
        getOrder();
    }

    private void getOrder() {
        String order = inputView.readOrder();
        try {
            Map<String, Integer> parsedMenu = parser.splitMenu(parser.splitOrder(order));
            orderDetails = new OrderDetails(parsedMenu);
        } catch (IllegalArgumentException e) {
            outputView.printErrorFromOrderMessage();
            getOrder();
        }
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }
}
