package christmas.controller;

import christmas.domain.service.EachAmountAdder;
import christmas.view.OutputView;

import java.text.DecimalFormat;
import java.util.Map;

import static christmas.domain.constants.Constant.AMOUNT_UNIT;

public class DiscountController {
    private static final String REWARD_FORMAT = "###,###";

    private final ScheduleController scheduleController = new ScheduleController();
    private final OrderController orderController;
    private final OutputView outputView = new OutputView();
    private final EachAmountAdder eachAmountAdder = new EachAmountAdder();

    private int purchaseAmount;

    public DiscountController(OrderController orderController) {
        this.orderController = orderController;
    }

    public void calcDiscount() {
        calcPurchaseAmountBeforeDiscount();
        showPurchaseAmountBeforeDiscount();
    }

    private void showPurchaseAmountBeforeDiscount() {
        outputView.printOrderAmountBeforeDiscountMessage();
        DecimalFormat decimalFormat = new DecimalFormat(REWARD_FORMAT);
        System.out.println(decimalFormat.format(purchaseAmount) + AMOUNT_UNIT.getMessage());
        outputView.printNewLine();
    }

    private void calcPurchaseAmountBeforeDiscount() {
        Map<String, Integer> orderDetails = orderController.getOrderDetails().getMenuDetails();
        for (String menuItem :
                orderDetails.keySet()) {
            purchaseAmount += eachAmountAdder.addEachAmount(menuItem, orderDetails);
        }
    }
}
